package es.daw.videojuegosjdbc.repository;

import es.daw.videojuegosjdbc.exception.AccesoDatosException;
import es.daw.videojuegosjdbc.model.Plataforma;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlataformaRepositoryJdbc implements PlataformaRepository {

    private final DataSource dataSource;

    @Override
    public Optional<Plataforma> findById(Integer codigo) {
        String sql = "SELECT codigo, nombre FROM plataforma WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar la plataforma " + codigo, e);
        }
    }

    @Override
    public List<Plataforma> findAll() {
        String sql = "SELECT codigo, nombre FROM plataforma ORDER BY nombre";
        List<Plataforma> plataformas = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                plataformas.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al listar las plataformas", e);
        }
        return plataformas;
    }

    @Override
    public void save(Plataforma plataforma) {
        String sql = "INSERT INTO plataforma (nombre) VALUES (?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, plataforma.getNombre());
            ps.executeUpdate();
            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    plataforma.setCodigo(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al guardar la plataforma", e);
        }
    }

    @Override
    public void update(Plataforma plataforma) {
        String sql = "UPDATE plataforma SET nombre = ? WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, plataforma.getNombre());
            ps.setInt(2, plataforma.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al actualizar la plataforma " + plataforma.getCodigo(), e);
        }
    }

    @Override
    public void delete(Integer codigo) {
        String sql = "DELETE FROM plataforma WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al borrar la plataforma " + codigo, e);
        }
    }

    private Plataforma mapRow(ResultSet rs) throws SQLException {
        return new Plataforma(rs.getInt("codigo"), rs.getString("nombre"));
    }
}
