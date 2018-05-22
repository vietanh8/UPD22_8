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
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ReceiverNum2 {
	
	DatagramSocket socketReceive;
	private JFrame frame;
	int return_port;
	InetAddress return_add;
	JTextArea tagui;
	/**
	 * Launch the application.
	 */
	public int receiver_Num() {
		byte[] numnhan= new byte[1024];
		int x=0;
		DatagramPacket receiverData= new DatagramPacket(numnhan, numnhan.length);
		try {
			socketReceive.receive(receiverData);
			byte[] packetNhan= receiverData.getData();
			 x= Integer.parseInt(new String(packetNhan, packetNhan.length).trim());
//			tfnhan.setText(tfnhan.getText()+""+x+" ");
			return_port= receiverData.getPort();
			return_add= receiverData.getAddress();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiverNum2 window = new ReceiverNum2();
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
	public ReceiverNum2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnChay = new JButton("Server ");
		btnChay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					socketReceive= new DatagramSocket(4359);
					JOptionPane.showMessageDialog(null, "Đã chạy Receiver");
					while(true) {
						int a=receiver_Num();
						int b=receiver_Num();
						if(a!=0 || b!=0) {
						guiKQ(a+b);
						tagui.append(String.valueOf(a+b+"\n"));
						}
						else break;
					}
				
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnChay.setBounds(10, 35, 416, 32);
		frame.getContentPane().add(btnChay);
		
		JLabel lblDLiuGi = new JLabel("DL gửi đi");
		lblDLiuGi.setBounds(10, 88, 85, 13);
		frame.getContentPane().add(lblDLiuGi);
		
		tagui = new JTextArea();
		tagui.setEditable(false);
		tagui.setBounds(85, 77, 341, 174);
		frame.getContentPane().add(tagui);
		
	
			
		}
	
	public void guiKQ(int x) {
		byte[] return_byte= String.valueOf(x).getBytes();
		DatagramPacket return_Packet = new DatagramPacket(return_byte, return_byte.length,return_add,return_port);
		try {
			socketReceive.send(return_Packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}