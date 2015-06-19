package untref.com.ar.kinect.pruebas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

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
		kinect.start(Kinect.DEPTH | Kinect.COLOR | Kinect.SKELETON | Kinect.XYZ
				| Kinect.PLAYER_INDEX);

		kinect.setElevationAngle(0);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int k = 0; k < 20000; k++) {

			byte[] colores = kinect.getColorFrame();
			float[] depth = kinect.getXYZ();

			System.out.println("Iteracion: " + k);

			// BufferedImage imagen = new BufferedImage(640, 480,
			// BufferedImage.TYPE_3BYTE_BGR);
			//
			// for (int i = 0; i < imagen.getHeight(); i++) {
			// for (int j = 0; j < imagen.getWidth(); j++) {
			//
			// int height = imagen.getWidth() * 4 * i;
			// int blue = j * 4 + height;
			// int green = j * 4 + 1 + height;
			// int red = j * 4 + 2 + height;
			// int alpha = j * 4 + 3 + height;
			//
			// Color color = new Color(colores[red] & 0xFF,
			// colores[green] & 0xFF, colores[blue] & 0xFF,
			// colores[alpha] & 0xFF);
			//
			// imagen.setRGB(j, i, color.getRGB());
			//
			// }
			// }
			//
			// for (int i = 0; i < 240; i++) {
			// for (int j = 0; j < 320; j++) {
			//
			// int height = 320 * 3 * i;
			// int x = j * 3 + height;
			// int y = j * 3 + 1 + height;
			// int z = j * 3 + 2 + height;
			//
			// if (j == 320 / 2 && i == 240 / 2) {
			// System.out.print(depth[x]);
			// System.out.print(" - ");
			// System.out.print(depth[y]);
			// System.out.print(" - ");
			// System.out.println(depth[z]);
			//
			// }
			//
			// if (true) {
			//
			// Color color = new Color((int) ((depth[z] / 4.0) * 255),
			// 0, 0);
			//
			// imagen.setRGB(j, i , color.getRGB());
			//
			// }
			//
			// }
			// }

			SensorData data = new SensorData(colores, depth);

			labelPrincipalImage.setIcon(new ImageIcon(data.getDepthImage()));

			this.getContentPane().add(labelPrincipalImage);

			try {
				Thread.sleep((1 / 10) * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		kinect.stop();
	}
}