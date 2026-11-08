package es.daw.videojuegosjdbc.repository;

import es.daw.videojuegosjdbc.exception.AccesoDatosException;
import es.daw.videojuegosjdbc.model.Plataforma;
import es.daw.videojuegosjdbc.model.Videojuego;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Capa de datos: la única que escribe SQL.
@Repository
@RequiredArgsConstructor
public class VideojuegoRepositoryJdbc implements VideojuegoRepository {

    // SELECT común a findById y findAll (JOIN para traer también el nombre de la plataforma)
    private static final String SELECT_BASE = """
            SELECT v.codigo, v.titulo, v.precio, v.stock,
                   p.codigo AS p_codigo, p.nombre AS p_nombre
            FROM videojuego v
            JOIN plataforma p ON v.codigo_plataforma = p.codigo
            """;

    private final DataSource dataSource;

    @Override
    public Optional<Videojuego> findById(Integer codigo) {
        String sql = SELECT_BASE + "WHERE v.codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar el videojuego " + codigo, e);
        }
    }

    @Override
    public List<Videojuego> findAll() {
        String sql = SELECT_BASE + "ORDER BY v.codigo";
        List<Videojuego> videojuegos = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                videojuegos.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al listar los videojuegos", e);
        }
        return videojuegos;
    }

    @Override
    public void save(Videojuego videojuego) {
        String sql = "INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES (?, ?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, videojuego.getTitulo());
            ps.setDouble(2, videojuego.getPrecio());
            ps.setInt(3, videojuego.getStock());
            ps.setInt(4, videojuego.getPlataforma().getCodigo());
            ps.executeUpdate();
            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    videojuego.setCodigo(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al guardar el videojuego", e);
        }
    }

    @Override
    public void update(Videojuego videojuego) {
        String sql = "UPDATE videojuego SET titulo = ?, precio = ?, stock = ?, codigo_plataforma = ? WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, videojuego.getTitulo());
            ps.setDouble(2, videojuego.getPrecio());
            ps.setInt(3, videojuego.getStock());
            ps.setInt(4, videojuego.getPlataforma().getCodigo());
            ps.setInt(5, videojuego.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al actualizar el videojuego " + videojuego.getCodigo(), e);
        }
    }

    @Override
    public void delete(Integer codigo) {
        String sql = "DELETE FROM videojuego WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al borrar el videojuego " + codigo, e);
        }
    }

    @Override
    public boolean existsByPlataformaCodigo(Integer codigoPlataforma) {
        String sql = "SELECT 1 FROM videojuego WHERE codigo_plataforma = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoPlataforma);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al comprobar los videojuegos de la plataforma " + codigoPlataforma, e);
        }
    }

    // Fila del ResultSet → objeto Videojuego
    private Videojuego mapRow(ResultSet rs) throws SQLException {
        Plataforma plataforma = new Plataforma(
                rs.getInt("p_codigo"),
                rs.getString("p_nombre"));

        return new Videojuego(
                rs.getInt("codigo"),
                rs.getString("titulo"),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                plataforma);
    }
}
