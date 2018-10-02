/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.mysql;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jluis
 */
@Entity
@Table(name = "lineal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lineal.findAll", query = "SELECT l FROM Lineal l"),
    @NamedQuery(name = "Lineal.findById", query = "SELECT l FROM Lineal l WHERE l.id = :id"),
    @NamedQuery(name = "Lineal.findByX", query = "SELECT l FROM Lineal l WHERE l.x = :x"),
    @NamedQuery(name = "Lineal.findByY", query = "SELECT l FROM Lineal l WHERE l.y = :y"),
    @NamedQuery(name = "Lineal.findByPendiente", query = "SELECT l FROM Lineal l WHERE l.pendiente = :pendiente"),
    @NamedQuery(name = "Lineal.findByOrdenada", query = "SELECT l FROM Lineal l WHERE l.ordenada = :ordenada")})
public class Lineal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "x")
    private Long x;
    @Column(name = "y")
    private Long y;
    @Column(name = "pendiente")
    private Long pendiente;
    @Column(name = "ordenada")
    private Long ordenada;

    public Lineal() {
    }

    public Lineal(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getPendiente() {
        return pendiente;
    }

    public void setPendiente(Long pendiente) {
        this.pendiente = pendiente;
    }

    public Long getOrdenada() {
        return ordenada;
    }

    public void setOrdenada(Long ordenada) {
        this.ordenada = ordenada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lineal)) {
            return false;
        }
        Lineal other = (Lineal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.mysql.Lineal[ id=" + id + " ]";
    }
    
}
