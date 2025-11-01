package br.com.fiap.dao;

import br.com.fiap.to.AcompanhanteTO;
import br.com.fiap.to.EnderecoTO;
import br.com.fiap.to.PacienteTO;
import br.com.fiap.to.TelefoneTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PacienteDAO {

    public PacienteTO save(PacienteTO paciente) {
        String sql = "INSERT INTO T_HR_PACIENTES(nm_paciente, em_paciente, sg_sexo, id_telefone, st_paciente, qt_consultas_restantes, qt_faltas, fl_possui_deficiencia, ds_tipo_deficiencia, fl_video_enviado, dt_nascimento, id_endereco, ds_preferencia_contato, dt_cadastro, dt_ultima_atualizacao, id_acompanhante) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            TelefoneTO telefonePaciente = paciente.getTelefone();
            TelefoneDAO telefoneDAO = new TelefoneDAO();
            telefonePaciente = telefoneDAO.save(telefonePaciente);

            EnderecoTO endereco = paciente.getEndereco();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            endereco = enderecoDAO.save(endereco);

            TelefoneTO telefoneAcomp = paciente.getAcompanhante().getTelefone();
            telefoneAcomp = telefoneDAO.save(telefoneAcomp);

            AcompanhanteTO acompanhante = paciente.getAcompanhante();
            acompanhante.setTelefone(telefoneAcomp);
            AcompanhanteDAO acompanhanteDAO = new AcompanhanteDAO();
            acompanhante = acompanhanteDAO.save(acompanhante);

            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getEmail());
            preparedStatement.setString(3, paciente.getSexo());
            preparedStatement.setLong(4, telefonePaciente.getId());
            preparedStatement.setString(5, paciente.getStatus());
            preparedStatement.setInt(6, paciente.getConsultasRestantes());
            preparedStatement.setInt(7, paciente.getFaltas());
            preparedStatement.setInt(8, paciente.isPossuiDeficiencia() ? 1 : 0);
            preparedStatement.setString(9, paciente.getTipoDeficiencia());
            preparedStatement.setInt(10, paciente.isVideoEnviado() ? 1 : 0);
            preparedStatement.setDate(11,  java.sql.Date.valueOf(paciente.getDataNascimento()));
            preparedStatement.setLong(12, endereco.getId());
            preparedStatement.setString(13, paciente.getPreferenciaContato());
            preparedStatement.setTimestamp(14, java.sql.Timestamp.valueOf(paciente.getDataCadastro()));
            preparedStatement.setTimestamp(15, java.sql.Timestamp.valueOf(paciente.getUltimaAtualizacao()));
            preparedStatement.setLong(16, acompanhante.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return paciente;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public PacienteTO update(PacienteTO paciente) {
        String sql = "UPDATE T_HR_PACIENTES SET nm_paciente = ?, em_paciente = ?, sg_sexo = ?, id_telefone = ?, st_paciente = ?, qt_consultas_restantes = ?, qt_faltas = ?, fl_possui_deficiencia = ?, ds_tipo_deficiencia = ?, fl_video_enviado = ?, dt_nascimento = ?, id_endereco = ?, ds_preferencia_contato = ?, dt_cadastro = ?, dt_ultima_atualizacao = ?, id_acompanhante = ? WHERE id_paciente = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            TelefoneTO telefonePaciente = paciente.getTelefone();
            TelefoneDAO telefoneDAO = new TelefoneDAO();
            telefonePaciente = telefoneDAO.update(telefonePaciente);

            EnderecoTO endereco = paciente.getEndereco();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            endereco = enderecoDAO.update(endereco);

            AcompanhanteTO acompanhante = paciente.getAcompanhante();
            if (acompanhante != null) {
                TelefoneTO telefoneAcomp = acompanhante.getTelefone();
                if (telefoneAcomp != null) {
                    telefoneAcomp = telefoneDAO.update(telefoneAcomp);
                    acompanhante.setTelefone(telefoneAcomp);
                }
                AcompanhanteDAO acompanhanteDAO = new AcompanhanteDAO();
                acompanhante = acompanhanteDAO.update(acompanhante);
            }

            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getEmail());
            preparedStatement.setString(3, paciente.getSexo());
            preparedStatement.setLong(4, telefonePaciente.getId());
            preparedStatement.setString(5, paciente.getStatus());
            preparedStatement.setInt(6, paciente.getConsultasRestantes());
            preparedStatement.setInt(7, paciente.getFaltas());
            preparedStatement.setInt(8, paciente.isPossuiDeficiencia() ? 1 : 0);
            preparedStatement.setString(9, paciente.getTipoDeficiencia());
            preparedStatement.setInt(10, paciente.isVideoEnviado() ? 1 : 0);
            preparedStatement.setDate(11, java.sql.Date.valueOf(paciente.getDataNascimento()));
            preparedStatement.setLong(12, endereco.getId());
            preparedStatement.setString(13, paciente.getPreferenciaContato());
            preparedStatement.setTimestamp(14, paciente.getDataCadastro() != null ? java.sql.Timestamp.valueOf(paciente.getDataCadastro()) : null);
            preparedStatement.setTimestamp(15, paciente.getUltimaAtualizacao() != null ? java.sql.Timestamp.valueOf(paciente.getUltimaAtualizacao()) : null);
            if (acompanhante != null) {
                preparedStatement.setLong(16, acompanhante.getId());
            } else {
                preparedStatement.setNull(16, java.sql.Types.NUMERIC);
            }
            preparedStatement.setLong(17, paciente.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return paciente;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            try (PreparedStatement ps1 = conn.prepareStatement(
                    "DELETE FROM T_HR_CONSULTAS WHERE id_paciente = ?")) {
                ps1.setLong(1, id);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement(
                    "DELETE FROM T_HR_TELEFONES WHERE id_telefone IN (SELECT id_telefone FROM T_HR_ACOMPANHANTES WHERE id_paciente = ?)")) {
                ps2.setLong(1, id);
                ps2.executeUpdate();
            }

            try (PreparedStatement ps3 = conn.prepareStatement(
                    "DELETE FROM T_HR_ACOMPANHANTES WHERE id_paciente = ?")) {
                ps3.setLong(1, id);
                ps3.executeUpdate();
            }

            try (PreparedStatement ps4 = conn.prepareStatement(
                    "DELETE FROM T_HR_TELEFONES WHERE id_telefone = (SELECT id_telefone FROM T_HR_PACIENTES WHERE id_paciente = ?)")) {
                ps4.setLong(1, id);
                ps4.executeUpdate();
            }

            try (PreparedStatement ps5 = conn.prepareStatement(
                    "DELETE FROM T_HR_ENDERECOS WHERE id_endereco = (SELECT id_endereco FROM T_HR_PACIENTES WHERE id_paciente = ?)")) {
                ps5.setLong(1, id);
                ps5.executeUpdate();
            }

            try (PreparedStatement ps6 = conn.prepareStatement(
                    "DELETE FROM T_HR_PACIENTES WHERE id_paciente = ?")) {
                ps6.setLong(1, id);
                return ps6.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public PacienteTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_PACIENTES WHERE id_paciente = ?";
        PacienteTO paciente = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                paciente = new PacienteTO();
                paciente.setId(resultSet.getLong("id_paciente"));
                paciente.setNome(resultSet.getString("nm_paciente"));
                paciente.setEmail(resultSet.getString("em_paciente"));
                paciente.setSexo(resultSet.getString("sg_sexo"));

                TelefoneDAO telefoneDAO = new TelefoneDAO();
                paciente.setTelefone(telefoneDAO.findById(resultSet.getLong("id_telefone")));

                paciente.setStatus(resultSet.getString("st_paciente"));
                paciente.setConsultasRestantes(resultSet.getInt("qt_consultas_restantes"));
                paciente.setFaltas(resultSet.getInt("qt_faltas"));
                paciente.setPossuiDeficiencia("1".equals(resultSet.getString("fl_possui_deficiencia")));
                paciente.setTipoDeficiencia(resultSet.getString("ds_tipo_deficiencia"));
                paciente.setVideoEnviado("1".equals(resultSet.getString("fl_video_enviado")));
                paciente.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());

                EnderecoDAO enderecoDAO = new EnderecoDAO();
                paciente.setEndereco(enderecoDAO.findById(resultSet.getLong("id_endereco")));

                paciente.setPreferenciaContato(resultSet.getString("ds_preferencia_contato"));
                paciente.setDataCadastro(resultSet.getTimestamp("dt_cadastro").toLocalDateTime());
                paciente.setUltimaAtualizacao(resultSet.getTimestamp("dt_ultima_atualizacao").toLocalDateTime());

                AcompanhanteDAO acompanhanteDAO = new AcompanhanteDAO();
                paciente.setAcompanhante(acompanhanteDAO.findById(resultSet.getLong("id_acompanhante")));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return paciente;
    }

    public ArrayList<PacienteTO> findAll() {
        String sql = "SELECT * FROM T_HR_PACIENTES ORDER BY id_paciente";
        ArrayList<PacienteTO> pacientes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    PacienteTO paciente = new PacienteTO();
                    paciente.setId(resultSet.getLong("id_paciente"));
                    paciente.setNome(resultSet.getString("nm_paciente"));
                    paciente.setEmail(resultSet.getString("em_paciente"));
                    paciente.setSexo(resultSet.getString("sg_sexo"));

                    TelefoneDAO telefoneDAO = new TelefoneDAO();
                    paciente.setTelefone(telefoneDAO.findById(resultSet.getLong("id_telefone")));

                    paciente.setStatus(resultSet.getString("st_paciente"));
                    paciente.setConsultasRestantes(resultSet.getInt("qt_consultas_restantes"));
                    paciente.setFaltas(resultSet.getInt("qt_faltas"));
                    paciente.setPossuiDeficiencia("1".equals(resultSet.getString("fl_possui_deficiencia")));
                    paciente.setTipoDeficiencia(resultSet.getString("ds_tipo_deficiencia"));
                    paciente.setVideoEnviado("1".equals(resultSet.getString("fl_video_enviado")));
                    paciente.setDataNascimento(resultSet.getDate("dt_nascimento").toLocalDate());

                    EnderecoDAO enderecoDAO = new EnderecoDAO();
                    paciente.setEndereco(enderecoDAO.findById(resultSet.getLong("id_endereco")));

                    paciente.setPreferenciaContato(resultSet.getString("ds_preferencia_contato"));
                    paciente.setDataCadastro(resultSet.getTimestamp("dt_cadastro").toLocalDateTime());
                    paciente.setUltimaAtualizacao(resultSet.getTimestamp("dt_ultima_atualizacao").toLocalDateTime());

                    AcompanhanteDAO acompanhanteDAO = new AcompanhanteDAO();
                    paciente.setAcompanhante(acompanhanteDAO.findById(resultSet.getLong("id_acompanhante")));

                    pacientes.add(paciente);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar pacientes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pacientes;
    }
}
