package application;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class CommonClass {
	protected static boolean type;
	protected static Scene scene;
	protected static Stage primaryStage = new Stage();
	protected static SLL<Person> users = new SLL<Person>();
	protected static SLL<Candidate> candidateList = new SLL<Candidate>();
	protected static SLL<Units> unitList = new SLL<Units>();
	protected static SLL<Band> bandList = new SLL<Band>();
	protected static SLL<Interview> interviewList = new SLL<Interview>();
	protected static Long id;
	protected static boolean hrCheck;
	protected static String name;
	protected static boolean cont = true;
	protected static int bandsNum = 0;

	public static void readBands() {
		// Reading Bands
		String fileName = "res\\Bands.txt";
		File file = new File(fileName);
		if (file.length() == 0) {
			System.out.println("Bands is empty");
			return;
		}
		try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName));) {
			while (cont) {
				Object obj = null;
				try {
					obj = objectIn.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (obj != null)
					bandList.addToTail((Band) obj);
				else
					cont = false;
			}

			objectIn.close();
		} catch (Exception ex) {
		}
	}

	public static void readCandidates() {
		// Reading Candidates
		cont = true;
		String fileName = "res\\Candidates.txt";
		File file = new File(fileName);
		if (file.length() == 0) {
			System.out.println("Candidate is empty");
			return;
		} else
			try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName));) {
				while (cont) {
					Object obj = null;
					try {
						obj = objectIn.readObject();
					} catch (Exception ex) {
					}
					if (obj != null)
						candidateList.addToTail((Candidate) obj);
					else
						cont = false;
				}
				objectIn.close();
			} catch (Exception ex) {
			}

	}

	public static void readUnits() {
		// Reading Units
		cont = true;
		String fileName = "res\\Units.txt";
		File file = new File(fileName);
		if (file.length() == 0) {
			System.out.println("Units is empty");
			return;
		}
		try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName));) {
			while (cont) {
				Object obj = null;
				try {
					obj = objectIn.readObject();
				} catch (Exception ex) {
				}
				if (obj != null)
					unitList.addToTail((Units) obj);
				else
					cont = false;
			}
			objectIn.close();
		} catch (Exception ex) {
		}
	}

	public static void readUsers() {
		// Reading Users
		cont = true;
		String fileName = "res\\Users.txt";
		File file = new File(fileName);
		cont = true;
		fileName = "res\\Users.txt";
		file = new File(fileName);
		if (file.length() == 0) {
			System.out.println("Users is empty");
			return;
		}
		try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName));) {
			while (cont) {
				Object obj = null;
				try {
					obj = objectIn.readObject();
				} catch (Exception ex) {
				}
				if (obj != null) {
					if (obj instanceof Interviewer)
						users.addToTail((Interviewer) obj);
					else
						users.addToTail((HR) obj);
				}

				else
					cont = false;
			}
			objectIn.close();
		} catch (Exception ex) {
		}
	}

	public static void readInterviews() {

	// Reading Interviews
		cont = true;
		String fileName = "res\\Interviewes.txt";
		File file = new File(fileName);

		if (file.length() == 0) {
			System.out.println("Interviewes is empty");
			return;
		}try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName));) {
			while (cont) {
				Object obj = null;
				try {
					obj = objectIn.readObject();
				} catch (Exception ex) {
				}
				if (obj != null)
					interviewList.addToTail((Interview) obj);
				else
					cont = false;
			}

			objectIn.close();
		} catch (Exception ex) {
		}
	}


	public static void writeBands() {
		String fileName = "res\\Bands.txt";

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			SLL<Band> list = bandList;
			SLLNode<Band> tmp = list.head;
			while (tmp != null) {
				oos.writeObject(tmp.info);
				tmp = tmp.next;
			}
			System.out.println("Saving");
			oos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

		// Writing Candidates
	public static void writeCandidates() {
		String fileName = "res\\Candidates.txt";

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			SLL<Candidate> list = candidateList;
			SLLNode<Candidate> tmp = list.head;
			while (tmp != null) {
				oos.writeObject(tmp.info);
				tmp = tmp.next;
			}
			System.out.println("Saving");
			oos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

		// Writing Units
	public static void writeUnits() {
		String fileName = "res\\Units.txt";

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			SLL<Units> list = unitList;
			SLLNode<Units> tmp = list.head;
			while (tmp != null) {
				oos.writeObject(tmp.info);
				tmp = tmp.next;
			}
			System.out.println("Saving");
			oos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

		// Writing Users
	public static void writeUsers() {
		String fileName = "res\\Users.txt";

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			SLL<Person> list = users;
			SLLNode<Person> tmp = list.head;
			while (tmp != null) {
				oos.writeObject(tmp.info);
				tmp = tmp.next;
			}
			System.out.println("Saving");
			oos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		// Writing Interviews

		public static void writeInterviews() {
			String fileName = "res\\Interviewes.txt";

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			SLL<Interview> list = interviewList;
			SLLNode<Interview> tmp = list.head;
			while (tmp != null) {
				oos.writeObject(tmp.info);
				tmp = tmp.next;
			}
			System.out.println("Saving");
			oos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
