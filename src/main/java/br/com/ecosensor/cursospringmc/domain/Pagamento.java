package br.com.ecosensor.cursospringmc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ecosensor.cursospringmc.domain.enums.EstadoPagamento;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_payment")
@Entity
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name = "col_status", nullable = false)
	private Integer status;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "id_order", nullable = false)
	@MapsId
	private Pedido order;
	
	protected Pagamento(Integer id, EstadoPagamento status, Pedido order) {
		super();
		this.id = id;
		this.status = status.getCode();
		this.order = order;
	}
	
	public EstadoPagamento getStatus() {
		return EstadoPagamento.toEnum(status);
	}
	
	public void setStatus(EstadoPagamento status) {
		this.status = status.getCode();
	}
	
}
