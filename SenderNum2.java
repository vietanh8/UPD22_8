import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class SenderNum2 {
	DatagramSocket senderSocket;

	private JFrame frame;
	private JTextField tfso1;
	private JTextField tfso2;
	private JTextField tfkq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SenderNum2 window = new SenderNum2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SenderNum2() {
		initialize();
	}
	public void sendPacket(int a) {
		String string_send= String.valueOf(a);
		byte[] byte_send = string_send.getBytes();
		try {
			InetAddress add= InetAddress.getByName("localhost");
			int port=4359;
			DatagramPacket packetSender= new DatagramPacket(byte_send, byte_send.length,add,port);
			senderSocket.send(packetSender);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnKtNi = new JButton("K\u1EBFt n\u1ED1i \u0111\u00EA");
		btnKtNi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 senderSocket= new DatagramSocket();
					 JOptionPane.showMessageDialog(null, "Sender kết nối thành công");
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnKtNi.setBounds(10, 10, 416, 21);
		frame.getContentPane().add(btnKtNi);
		
		JLabel lblSThNht = new JLabel("S\u1ED1 th\u1EE9 nh\u1EA5t");
		lblSThNht.setBounds(20, 41, 70, 21);
		frame.getContentPane().add(lblSThNht);
		
		tfso1 = new JTextField();
		tfso1.setBounds(104, 41, 96, 19);
		frame.getContentPane().add(tfso1);
		tfso1.setColumns(10);
		
		tfso2 = new JTextField();
		tfso2.setColumns(10);
		tfso2.setBounds(104, 92, 96, 19);
		frame.getContentPane().add(tfso2);
		
		JLabel lblSTh = new JLabel("S\u1ED1 th\u1EE9 2");
		lblSTh.setBounds(20, 95, 70, 21);
		frame.getContentPane().add(lblSTh);
		
		JButton btnGiLnReceiver = new JButton("Send");
		btnGiLnReceiver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendPacket(Integer.parseInt(tfso1.getText()));
				sendPacket(Integer.parseInt(tfso2.getText()));
				nhanKQ();
			}
		});
		btnGiLnReceiver.setBounds(248, 44, 155, 47);
		frame.getContentPane().add(btnGiLnReceiver);
		
		JLabel lblKtQuReceiver = new JLabel("Kết quả Receiver");
		lblKtQuReceiver.setBounds(10, 142, 152, 47);
		frame.getContentPane().add(lblKtQuReceiver);
		
		tfkq = new JTextField();
		tfkq.setEditable(false);
		tfkq.setColumns(10);
		tfkq.setBounds(174, 142, 131, 47);
		frame.getContentPane().add(tfkq);
	}
	public void nhanKQ() {
		byte[] return_byte= new byte[1024];
		DatagramPacket return_packet= new DatagramPacket(return_byte, return_byte.length);
		try {
			senderSocket.receive(return_packet);
			byte[] final_return = return_packet.getData();
			tfkq.setText(new String(final_return,final_return.length).trim());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}