package smartgate.capstonespringboot.exception;

public class ReminderNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5650541100494197336L;

	public ReminderNotFoundException(Long id) {
    super("Could not find reminder " + id);
  }
}
