package model;

public class Veterinario {
  private int id;
  private String nome, crmv;
  private double salario;

  public Veterinario() { }

  public Veterinario(int id) {
    this.id = id;
  }

  public Veterinario(String nome, String crmv) {
    this.nome = nome;
    this.crmv = crmv;
  }

  public Veterinario(String nome, String crmv, double salario) {
    this.nome = nome;
    this.crmv = crmv;
    this.salario = salario;
  }

  public Veterinario(int id, String nome, String crmv, double salario) {
    this.id = id;
    this.nome = nome;
    this.crmv = crmv;
    this.salario = salario;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getCrmv() {
    return crmv;
  }
  public void setCrmv(String crmv) {
    this.crmv = crmv;
  }
  public double getSalario() {
    return salario;
  }
  public void setSalario(double salario) {
    this.salario = salario;
  }

  
}
