package video;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

// TODO: investigate if we can refactor common parts with AbstractTCPManager
public abstract class AbstractManager implements Runnable {

	protected InetAddress inetaddr = null;
	protected DatagramSocket socket = null;
	protected boolean doStop = false;
	protected boolean connected = false;
	protected Thread thread = null;

	public AbstractManager(InetAddress inetaddr) {
		this.inetaddr = inetaddr;
	}

	public boolean connect(int port) {
		try {
			socket = new DatagramSocket(port);
			socket.setSoTimeout(3000);
		} catch (SocketException e) {
			e.printStackTrace();
			connected = false;
			return false;
		}
		connected = true;
		return true;
	}

	public boolean isConnected() {
		return connected;
	}

	public void close() {
		if (socket != null) {
			socket.close();
		}
		connected = false;
	}

	public void stop() {
		System.out.println("AbstractManager: Stopping " + getClass().getSimpleName());
		if (thread != null) {
			thread.interrupt();
			doStop = true;
			thread = null;
		}
	}

	protected void ticklePort(int port) {
		byte[] buf = { 0x01, 0x00, 0x00, 0x00 };
		DatagramPacket packet = new DatagramPacket(buf, buf.length, inetaddr, port);
		try {
			if (socket != null) {
				socket.send(packet);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		System.out.println("AbstractManager: Starting " + getClass().getSimpleName());
		if (thread == null) {
			doStop = false;
			String name = getClass().getSimpleName();
			thread = new Thread(this, name);
			thread.start();
		} else {
			System.out.println("Already started before " + getClass().getSimpleName());
		}
	}
}
