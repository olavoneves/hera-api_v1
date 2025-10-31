package br.com.fiap.dao;

import br.com.fiap.to.ConsultaTO;
import br.com.fiap.to.MedicoTO;
import br.com.fiap.to.PacienteTO;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {

    public ConsultaTO save(ConsultaTO consulta) {
        String sql = "INSERT INTO T_HR_CONSULTAS(id_paciente, id_medico, dt_consulta, hr_consulta, st_consulta, tp_consulta, ds_observacoes, lk_teleconsulta) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"id_consulta"})) {
            preparedStatement.setLong(1, consulta.getPaciente().getId());
            preparedStatement.setLong(2, consulta.getMedico().getId());
            preparedStatement.setDate(3, Date.valueOf(consulta.getDataConsulta()));
            preparedStatement.setTime(4, Time.valueOf(consulta.getHorarioConsulta()));
            preparedStatement.setString(5, consulta.getStatus());
            preparedStatement.setString(6, consulta.getTipoConsulta());
            preparedStatement.setString(7, consulta.getObservacoes());
            preparedStatement.setString(8, consulta.getLinkTeleconsulta());

            if (preparedStatement.executeUpdate() > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    consulta.setId(generatedKeys.getLong(1));
                }

                PacienteDAO pacienteDAO = new PacienteDAO();
                PacienteTO pacienteCompleto = pacienteDAO.findById(consulta.getPaciente().getId());

                MedicoDAO medicoDAO = new MedicoDAO();
                MedicoTO medicoCompleto = medicoDAO.findById(consulta.getMedico().getId());

                consulta.setPaciente(pacienteCompleto);
                consulta.setMedico(medicoCompleto);

                return consulta;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ConsultaTO update(ConsultaTO consulta) {
        String sql = "UPDATE T_HR_CONSULTAS SET id_paciente = ?, id_medico = ?, dt_consulta = ?, hr_consulta = ?, st_consulta = ?, tp_consulta = ?, ds_observacoes = ?, lk_teleconsulta = ? WHERE id_consulta = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, consulta.getPaciente().getId());
            preparedStatement.setLong(2, consulta.getMedico().getId());
            preparedStatement.setDate(3, Date.valueOf(consulta.getDataConsulta()));
            preparedStatement.setTime(4, Time.valueOf(consulta.getHorarioConsulta()));
            preparedStatement.setString(5, consulta.getStatus());
            preparedStatement.setString(6, consulta.getTipoConsulta());
            preparedStatement.setString(7, consulta.getObservacoes());
            preparedStatement.setString(8, consulta.getLinkTeleconsulta());
            preparedStatement.setLong(9, consulta.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return consulta;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_HR_CONSULTAS WHERE id_consulta = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ConsultaTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_CONSULTAS WHERE id_consulta = ?";
        ConsultaTO consulta = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                consulta = new ConsultaTO();
                consulta.setId(resultSet.getLong("id_consulta"));

                PacienteDAO pacienteDAO = new PacienteDAO();
                consulta.setPaciente(pacienteDAO.findById(resultSet.getLong("id_paciente")));

                MedicoDAO medicoDAO = new MedicoDAO();
                consulta.setMedico(medicoDAO.findById(resultSet.getLong("id_medico")));

                consulta.setDataConsulta(resultSet.getDate("dt_consulta").toLocalDate());
                consulta.setHorarioConsulta(resultSet.getTime("hr_consulta").toLocalTime());
                consulta.setStatus(resultSet.getString("st_consulta"));
                consulta.setStatus(resultSet.getString("tp_consulta"));
                consulta.setStatus(resultSet.getString("ds_observacoes"));
                consulta.setStatus(resultSet.getString("lk_teleconsulta"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consulta;
    }

    public ArrayList<ConsultaTO> findAll() {
        String sql = "SELECT * FROM T_HR_CONSULTAS ORDER BY id_consulta";
        ArrayList<ConsultaTO> consultas = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ConsultaTO consulta = new ConsultaTO();
                consulta.setId(rs.getLong("id_consulta"));

                PacienteDAO pacienteDAO = new PacienteDAO();
                consulta.setPaciente(pacienteDAO.findById(rs.getLong("id_paciente")));

                MedicoDAO medicoDAO = new MedicoDAO();
                consulta.setMedico(medicoDAO.findById(rs.getLong("id_medico")));

                consulta.setDataConsulta(rs.getDate("dt_consulta").toLocalDate());
                consulta.setHorarioConsulta(rs.getTime("hr_consulta").toLocalTime());
                consulta.setStatus(rs.getString("st_consulta"));
                consulta.setTipoConsulta(rs.getString("tp_consulta"));
                consulta.setObservacoes(rs.getString("ds_observacoes"));
                consulta.setLinkTeleconsulta(rs.getString("lk_teleconsulta"));

                consultas.add(consulta);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar consultas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return consultas;
    }
}
