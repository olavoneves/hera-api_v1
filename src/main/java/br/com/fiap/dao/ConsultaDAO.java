package br.com.fiap.dao;

import br.com.fiap.to.ConsultaTO;
import br.com.fiap.to.MedicoTO;
import br.com.fiap.to.PacienteTO;
import br.com.fiap.to.TelefoneTO;

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
        String sql = """
        SELECT 
            c.id_consulta,
            c.dt_consulta,
            c.hr_consulta,
            c.st_consulta,
            c.tp_consulta,
            c.ds_observacoes,
            c.lk_teleconsulta,
            
            p.id_paciente,
            p.nm_paciente,
            p.em_paciente,
            p.sg_sexo,
            p.st_paciente,
            
            m.id_medico,
            m.nm_medico,
            m.cd_crm,
            m.ds_especialidade,
            m.em_medico,
            m.st_medico,
            
            t1.id_telefone AS id_tel_paciente,
            t1.nr_ddd AS ddd_paciente,
            t1.nr_telefone AS num_paciente,
            t1.tp_telefone AS tipo_paciente,
            
            t2.id_telefone AS id_tel_medico,
            t2.nr_ddd AS ddd_medico,
            t2.nr_telefone AS num_medico,
            t2.tp_telefone AS tipo_medico
            
        FROM T_HR_CONSULTAS c
        INNER JOIN T_HR_PACIENTES p ON c.id_paciente = p.id_paciente
        INNER JOIN T_HR_MEDICOS m ON c.id_medico = m.id_medico
        LEFT JOIN T_HR_TELEFONES t1 ON p.id_telefone = t1.id_telefone
        LEFT JOIN T_HR_TELEFONES t2 ON m.id_telefone = t2.id_telefone
        ORDER BY c.id_consulta
        """;

        ArrayList<ConsultaTO> consultas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ConsultaTO consulta = new ConsultaTO();
                consulta.setId(rs.getLong("id_consulta"));
                consulta.setDataConsulta(rs.getDate("dt_consulta").toLocalDate());
                consulta.setHorarioConsulta(rs.getTime("hr_consulta").toLocalTime());
                consulta.setStatus(rs.getString("st_consulta"));
                consulta.setTipoConsulta(rs.getString("tp_consulta"));
                consulta.setObservacoes(rs.getString("ds_observacoes"));
                consulta.setLinkTeleconsulta(rs.getString("lk_teleconsulta"));

                PacienteTO paciente = new PacienteTO();
                paciente.setId(rs.getLong("id_paciente"));
                paciente.setNome(rs.getString("nm_paciente"));
                paciente.setEmail(rs.getString("em_paciente"));
                paciente.setSexo(rs.getString("sg_sexo"));
                paciente.setStatus(rs.getString("st_paciente"));

                TelefoneTO telPac = new TelefoneTO();
                telPac.setId(rs.getLong("id_tel_paciente"));
                telPac.setDdd(rs.getString("ddd_paciente"));
                telPac.setNumero(rs.getString("num_paciente"));
                telPac.setTipoDeTelefone(rs.getString("tipo_paciente"));
                paciente.setTelefone(telPac);

                MedicoTO medico = new MedicoTO();
                medico.setId(rs.getLong("id_medico"));
                medico.setNome(rs.getString("nm_medico"));
                medico.setCrm(rs.getString("cd_crm"));
                medico.setEspecialidade(rs.getString("ds_especialidade"));
                medico.setEmail(rs.getString("em_medico"));
                medico.setStatus(rs.getString("st_medico"));

                TelefoneTO telMed = new TelefoneTO();
                telMed.setId(rs.getLong("id_tel_medico"));
                telMed.setDdd(rs.getString("ddd_medico"));
                telMed.setNumero(rs.getString("num_medico"));
                telMed.setTipoDeTelefone(rs.getString("tipo_medico"));
                medico.setTelefone(telMed);

                consulta.setPaciente(paciente);
                consulta.setMedico(medico);

                consultas.add(consulta);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar consultas: " + e.getMessage());
        }

        return consultas;
    }
}
