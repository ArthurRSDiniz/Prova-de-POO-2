package AlunoIMC;

public class Aluno {
    private int id; // Necessário para Alterar e Remover
    private String nome, endereco, telefone, cpf, tipoSanguineo, fatorRH, curso, contatoEmergencia, telefoneEmergencia;
    private double peso, altura, imc;

   //gets e sets
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(String tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }

    public String getFatorRH() { return fatorRH; }
    public void setFatorRH(String fatorRH) { this.fatorRH = fatorRH; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getContatoEmergencia() { return contatoEmergencia; }
    public void setContatoEmergencia(String contatoEmergencia) { this.contatoEmergencia = contatoEmergencia; }

    public String getTelefoneEmergencia() { return telefoneEmergencia; }
    public void setTelefoneEmergencia(String telefoneEmergencia) { this.telefoneEmergencia = telefoneEmergencia; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }
}

// Para estudar: Pense no Objeto como uma caixa com etiquetas. O private tranca a caixa,
// e os Getters/Setters são os buraquinhos por onde você coloca e tira informação.