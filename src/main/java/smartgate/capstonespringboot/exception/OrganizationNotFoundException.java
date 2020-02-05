package smartgate.capstonespringboot.exception;

public class OrganizationNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5650541100494197336L;

	public OrganizationNotFoundException(Long id) {
		super("Could not find organization " + id);
	}
	
	public OrganizationNotFoundException(String id) {
		super("Could not find organization " + id);
	}
	
	public OrganizationNotFoundException() {
		super("Could not find organization " );
	}
}
