package com.viridis.recruter.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade que representa uma ordem de serviço de um equipamento
 * 
 * @author mauro.chaves
 *
 */
@Entity
@Table(name = "ordem_servico")
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ordem_servico")
	private Long id;

	@Column(name = "observacao_ordem_servico", length = 255, nullable = true)
	private String observacao;

	@Column(name = "codigo_ordem_servico", length = 10, nullable = false)
	private String codigo;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_equipamento", referencedColumnName = "id_equipamento", nullable = false)
	private Equipamento equipamento;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@Column(name = "situacao_ordem_servico")
	private SituacaoOrdemServico situacaoOrdemServico;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@Column(name = "tipo_servico")
	private TipoServico tipoServico;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_abertura", unique = true, nullable = false, length = 10)
	private Date dataAbertura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public SituacaoOrdemServico getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	public void setSituacaoOrdemServico(SituacaoOrdemServico situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

}
