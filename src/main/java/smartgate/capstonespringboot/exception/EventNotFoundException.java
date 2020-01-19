package smartgate.capstonespringboot.exception;

public class EventNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5650541100494197336L;

	public EventNotFoundException(Long id) {
    super("Could not find event " + id);
  }
}
