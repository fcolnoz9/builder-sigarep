package sigarep.modelos.seguridad;

/**
 * 
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Usuario extends User {
	private static final long serialVersionUID = -2585113257594639651L;
	private Date lastAccessDate = new Date();

	/**
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public Usuario(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public Usuario(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities 
	 */
	public Usuario(String username, String password, String[] authoStrs) {
		super(username, password, true, true, true, true, toGrantedAuthorities(authoStrs));
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	
	private static Collection<? extends GrantedAuthority> toGrantedAuthorities(String[] authoStrs){
		ArrayList<GrantedAuthority> arrList = new ArrayList<GrantedAuthority>(authoStrs.length);
		for(String gaStr : authoStrs){
			arrList.add(new SimpleGrantedAuthority(gaStr));
		}
		return arrList;
	}
}
