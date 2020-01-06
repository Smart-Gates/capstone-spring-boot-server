package smartgate.capstonespringboot.meetings;

public class MeetingNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5650541100494197336L;

	MeetingNotFoundException(Long id) {
    super("Could not find meeting " + id);
  }
}
