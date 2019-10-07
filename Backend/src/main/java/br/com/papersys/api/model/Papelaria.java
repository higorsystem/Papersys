package br.com.papersys.api.model;

import javax.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "papelaria")
public class Papelaria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idPapelaria")
  private long id;

  @Column(name = "codBarras")
  private String codBarras;

  @Column(name = "nome")
  private String nome;

  @Column(name = "descricao")
  private String descricao;

  @Column(name = "quantidade")
  private Double quantidade;

  @Column(name = "categoria")
  private String categoria;

  public Papelaria(
      Integer id,
      String codBarras,
      String nome,
      String descricao,
      Double quantidade,
      String categoria) {
    this.id = id;
    this.codBarras = codBarras;
    this.nome = nome;
    this.descricao = descricao;
    this.quantidade = quantidade;
    this.categoria = categoria;
  }

  public Papelaria() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCodBarras() {
    return codBarras;
  }

  public void setCodBarras(String codBarras) {
    this.codBarras = codBarras;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Double quantidade) {
    this.quantidade = quantidade;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, true);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, true);
  }
}
