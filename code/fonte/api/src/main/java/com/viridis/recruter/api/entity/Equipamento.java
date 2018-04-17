package com.viridis.recruter.api.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que representa um equipamento de dispositivo elétrico
 * 
 * @author mauro.chaves
 *
 */
@Entity
@Table(name = "equipamento")
public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_equipamento")
	private Long id;

	@Column(name = "descricao_equipamento", length = 50, nullable = false)
	private String descricao;

	@Column(name = "codigo_equipamento", length = 10, nullable = false)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	private Fabricante fabricante;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@Column(name = "tipo_equipamento")
	private TipoEquipamento tipoEquipamento;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@Column(name = "status_equipamento")
	private StatusEquipamento statusEquipamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public StatusEquipamento getStatusEquipamento() {
		return statusEquipamento;
	}

	public void setStatusEquipamento(StatusEquipamento statusEquipamento) {
		this.statusEquipamento = statusEquipamento;
	}

}
