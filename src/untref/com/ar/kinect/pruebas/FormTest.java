package untref.com.ar.kinect.pruebas;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FormTest extends JFrame {

	private JLabel labelPrincipalImage;
	private JScrollPane scrollPane;
	private JPanel contentPane;

	public FormTest() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 640, 480);

		labelPrincipalImage = new JLabel();
		labelPrincipalImage.setHorizontalAlignment(JLabel.CENTER);

		contentPane = new JPanel();
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(labelPrincipalImage);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

	public void mostrarImagen() {

		Kinect kinect = new Kinect();

		kinect.stop();
		kinect.start(Kinect.DEPTH | Kinect.COLOR | Kinect.SKELETON | Kinect.XYZ
				| Kinect.PLAYER_INDEX);

		kinect.setElevationAngle(0);
		kinect.setColorResolution(640, 480);
		kinect.setDepthResolution(640, 480);

		// Espera para el encendido
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int k = 0;
		while (true) {

			System.out.println("Iteracion: " + k);

			SensorData data = new SensorData(kinect);

			labelPrincipalImage.setIcon(new ImageIcon(data
					.getImagenProfundidad()));
			this.getContentPane().add(labelPrincipalImage);

			// Cuadros por segundo
			try {
				Thread.sleep((1 / 10) * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			k++;
		}
	}
}
