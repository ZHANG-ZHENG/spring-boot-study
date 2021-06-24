package top.zhost.test;

import java.io.Serializable;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


@NodeEntity(label = "user")
public class UserNode implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue
    private Long identity;
 
    private  String name;
 

 


	public Long getIdentity() {
		return identity;
	}

	public void setIdentity(Long identity) {
		this.identity = identity;
	}

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 

}
