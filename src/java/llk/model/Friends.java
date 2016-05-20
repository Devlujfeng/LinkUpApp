/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llk.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tictaclu
 */
@Entity
@Table(name = "friends")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friends.findAll", query = "SELECT f FROM Friends f"),
    @NamedQuery(name = "Friends.findByIdFriends", query = "SELECT f FROM Friends f WHERE f.idFriends = :idFriends"),
    @NamedQuery(name = "Friends.findByFriendsEmail", query = "SELECT f FROM Friends f WHERE f.friendsEmail = :friendsEmail")})
public class Friends implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFriends")
    private Integer idFriends;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "friendsEmail")
    private String friendsEmail;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Users email;

    public Friends() {
    }

    public Friends(Integer idFriends) {
        this.idFriends = idFriends;
    }

    public Friends(Integer idFriends, String friendsEmail) {
        this.idFriends = idFriends;
        this.friendsEmail = friendsEmail;
    }

    public Integer getIdFriends() {
        return idFriends;
    }

    public void setIdFriends(Integer idFriends) {
        this.idFriends = idFriends;
    }

    public String getFriendsEmail() {
        return friendsEmail;
    }

    public void setFriendsEmail(String friendsEmail) {
        this.friendsEmail = friendsEmail;
    }

    public Users getEmail() {
        return email;
    }

    public void setEmail(Users email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFriends != null ? idFriends.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friends)) {
            return false;
        }
        Friends other = (Friends) object;
        if ((this.idFriends == null && other.idFriends != null) || (this.idFriends != null && !this.idFriends.equals(other.idFriends))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "llk.model.Friends[ idFriends=" + idFriends + " ]";
    }
    
}
