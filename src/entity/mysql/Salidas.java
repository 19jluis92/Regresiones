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
@Table(name = "salidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salidas.findAll", query = "SELECT s FROM Salidas s"),
    @NamedQuery(name = "Salidas.findById", query = "SELECT s FROM Salidas s WHERE s.id = :id"),
    @NamedQuery(name = "Salidas.findByVenta", query = "SELECT s FROM Salidas s WHERE s.venta = :venta"),
    @NamedQuery(name = "Salidas.findByNombre", query = "SELECT s FROM Salidas s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Salidas.findByDireccion", query = "SELECT s FROM Salidas s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "Salidas.findByTelefono", query = "SELECT s FROM Salidas s WHERE s.telefono = :telefono"),
    @NamedQuery(name = "Salidas.findByCodigo", query = "SELECT s FROM Salidas s WHERE s.codigo = :codigo")})
public class Salidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "venta")
    private double venta;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "codigo")
    private String codigo;

    public Salidas() {
    }

    public Salidas(Integer id) {
        this.id = id;
    }

    public Salidas(Integer id, double venta) {
        this.id = id;
        this.venta = venta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        if (!(object instanceof Salidas)) {
            return false;
        }
        Salidas other = (Salidas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.mysql.Salidas[ id=" + id + " ]";
    }
    
}
