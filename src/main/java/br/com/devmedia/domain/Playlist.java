package br.com.devmedia.domain;

import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Size(min = 2, max = 60)
	@Column(nullable = false, length = 60)
	private String nome;
	
	@NotEmpty
	@Column(nullable = false)
	private String descricao;
	
	@OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
	private List<Musica> musicas;
	
	public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
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
 
    public List<Musica> getMusicas() {
        return musicas;
    }
 
    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
	
	
	
	
	
}
