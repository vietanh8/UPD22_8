import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReverReceier {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket socketReceiver = new DatagramSocket(5600);
			System.out.println("Receiver started");
			
			byte [] chuoiNhan = new byte[1024];
			DatagramPacket receiverData= new DatagramPacket(chuoiNhan, chuoiNhan.length);
			socketReceiver.receive(receiverData);
			
			byte[] packetNhan = receiverData.getData();
			String chuoi = (new String (packetNhan, packetNhan.length).trim());
			StringBuffer send = new StringBuffer(chuoi);
			String sendText = send.reverse().toString();
			
			byte[] packetChuoiNguc = sendText.getBytes();
			InetAddress address = receiverData.getAddress();
			int port = receiverData.getPort();
			DatagramPacket sendData = new 
					DatagramPacket(packetChuoiNguc, packetChuoiNguc.length, address, port);
			
			socketReceiver.send(sendData);
			socketReceiver.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
