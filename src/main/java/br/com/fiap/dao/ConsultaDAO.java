package br.com.fiap.dao;

import br.com.fiap.to.ConsultaTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

public class ConsultaDAO {

    public ConsultaTO save(ConsultaTO consulta) {
        String sql = "INSERT INTO T_HR_CONSULTAS(id_paciente, id_medico, dt_consulta, hr_consulta, st_consulta, tp_consulta, ds_observacoes, lk_teleconsulta) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, consulta.getPaciente().getId());
            preparedStatement.setLong(2, consulta.getMedico().getId());
            preparedStatement.setDate(3, Date.valueOf(consulta.getDataConsulta()));
            preparedStatement.setTime(4, Time.valueOf(consulta.getHorarioConsulta()));
            preparedStatement.setString(5, consulta.getStatus());
            preparedStatement.setString(6, consulta.getTipoConsulta());
            preparedStatement.setString(7, consulta.getObservacoes());
            preparedStatement.setString(8, consulta.getLinkTeleconsulta());
            if (preparedStatement.executeUpdate() > 0) {
                return consulta;
            } else {
                return null;
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

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    ConsultaTO consulta = new ConsultaTO();
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
                    consultas.add(consulta);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar consultas: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consultas;
    }
}
