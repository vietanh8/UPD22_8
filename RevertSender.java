import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RevertSender {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket socketSender = new DatagramSocket();
			System.out.println("Sender connected");
			
			String chuoi = "cc";
			byte [] packetChuoi=chuoi.getBytes();
			int lenght = packetChuoi.length;
			InetAddress address = InetAddress.getByName("locolhost");
			int port = 5600;
			
			
			DatagramPacket sendPacket = new DatagramPacket(packetChuoi, lenght, address, port);
			socketSender.send(sendPacket);
			
			byte [] nhan = new byte[1024];
			DatagramPacket reveive = new DatagramPacket(nhan, nhan.length);
			socketSender.receive(reveive);
			byte[] chuoiNhan = reveive.getData();
			String chuoiNguoc = (new String (chuoiNhan,chuoiNhan.length).trim());
			System.out.println("Nhan la: "+chuoiNguoc);
			socketSender.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
