package untref.com.ar.kinect.pruebas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FormTest extends JFrame {

	private Kinect kinect;
	private Timer timer;
	private SensorData data;
	private JLabel labelPrincipalImagen;
	private JLabel label_x_value;
	private JLabel label_y_value;
	private JLabel label_color_value;
	private JLabel label_distancia_value;
	private JRadioButton radioColor;
	private JRadioButton radioProfundidad;
	private JRadioButton radioAmbos;

	public FormTest() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);

		setupUI();
		this.pack();

		setupKinect();
		this.timer = new Timer();
		long period = (1 / 10) * 1000;
		period = 100;
		// this.timer.scheduleAtFixedRate(new Tarea(this), 0, period);

		this.setVisible(true);
	}

	private void setupUI() {

		this.setContentPane(new JPanel(new GridBagLayout()));

		/*
		 * Imagen
		 */
		labelPrincipalImagen = new JLabel();
		labelPrincipalImagen.setPreferredSize(new Dimension(640, 480));
		labelPrincipalImagen.setBorder(BorderFactory
				.createLineBorder(Color.black));

		GridBagConstraints c;
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 10;
		this.getContentPane().add(labelPrincipalImagen, c);

		labelPrincipalImagen.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

				updateValues(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		/*
		 * Info
		 */
		// X
		JLabel label_x = new JLabel();
		label_x.setPreferredSize(new Dimension(100, 50));
		label_x.setBorder(BorderFactory.createLineBorder(Color.black));
		label_x.setHorizontalAlignment(JLabel.CENTER);
		label_x.setText("X");

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		this.getContentPane().add(label_x, c);

		// Y
		JLabel label_y = new JLabel();
		label_y.setPreferredSize(new Dimension(100, 50));
		label_y.setBorder(BorderFactory.createLineBorder(Color.black));
		label_y.setHorizontalAlignment(JLabel.CENTER);
		label_y.setText("Y");

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		this.getContentPane().add(label_y, c);

		// Color
		JLabel label_color = new JLabel();
		label_color.setPreferredSize(new Dimension(100, 50));
		label_color.setBorder(BorderFactory.createLineBorder(Color.black));
		label_color.setHorizontalAlignment(JLabel.CENTER);
		label_color.setText("Color (R,G,B)");

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		this.getContentPane().add(label_color, c);

		// Distancia
		JLabel label_distancia = new JLabel();
		label_distancia.setPreferredSize(new Dimension(100, 50));
		label_distancia.setBorder(BorderFactory.createLineBorder(Color.black));
		label_distancia.setHorizontalAlignment(JLabel.CENTER);
		label_distancia.setText("Distancia");

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		this.getContentPane().add(label_distancia, c);

		// X-value
		label_x_value = new JLabel();
		label_x_value.setPreferredSize(new Dimension(100, 50));
		label_x_value.setBorder(BorderFactory.createLineBorder(Color.black));
		label_x_value.setHorizontalAlignment(JLabel.CENTER);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		this.getContentPane().add(label_x_value, c);

		// Y-value
		label_y_value = new JLabel();
		label_y_value.setPreferredSize(new Dimension(100, 50));
		label_y_value.setBorder(BorderFactory.createLineBorder(Color.black));
		label_y_value.setHorizontalAlignment(JLabel.CENTER);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		this.getContentPane().add(label_y_value, c);

		// Color-value
		label_color_value = new JLabel();
		label_color_value.setPreferredSize(new Dimension(100, 50));
		label_color_value
				.setBorder(BorderFactory.createLineBorder(Color.black));
		label_color_value.setHorizontalAlignment(JLabel.CENTER);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		this.getContentPane().add(label_color_value, c);

		// Distancia-value
		label_distancia_value = new JLabel();
		label_distancia_value.setPreferredSize(new Dimension(100, 50));
		label_distancia_value.setBorder(BorderFactory
				.createLineBorder(Color.black));
		label_distancia_value.setHorizontalAlignment(JLabel.CENTER);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		this.getContentPane().add(label_distancia_value, c);

		/*
		 * Radio buttons
		 */
		JPanel panelRadio = new JPanel(new GridBagLayout());
		panelRadio.setBorder(BorderFactory
				.createTitledBorder("Entrada de datos"));

		radioColor = new JRadioButton("Color");
		radioProfundidad = new JRadioButton("Profundidad");
		radioAmbos = new JRadioButton("Ambos");
		radioColor.setFocusPainted(false);
		radioProfundidad.setFocusPainted(false);
		radioAmbos.setFocusPainted(false);
		radioColor.setSelected(true);

		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.insets = new Insets(5, 5, 5, 5);
		this.getContentPane().add(panelRadio, c);
		panelRadio.add(radioColor, c);

		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		this.getContentPane().add(panelRadio, c);
		panelRadio.add(radioProfundidad, c);

		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		this.getContentPane().add(panelRadio, c);
		panelRadio.add(radioAmbos, c);

		c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		this.getContentPane().add(panelRadio, c);

		ButtonGroup radioButtons = new ButtonGroup();
		radioButtons.add(radioColor);
		radioButtons.add(radioProfundidad);
		radioButtons.add(radioAmbos);

		Container contentPane = this.getContentPane();
		JScrollPane scrollPane = new JScrollPane(contentPane);
		scrollPane.setBorder(null);
		this.setContentPane(scrollPane);
	}

	private void updateValues(MouseEvent e) {

		label_x_value.setText(String.valueOf(e.getX()));
		label_y_value.setText(String.valueOf(e.getY()));
		Color color = data.getColorEnPixel(e.getX(), e.getY());
		label_color_value.setText("(" + color.getRed() + "," + color.getGreen()
				+ "," + color.getBlue() + ")");
		label_distancia_value.setText(String.valueOf(data.getDistancia(
				e.getX(), e.getY()) / 100) + " cm");
	}

	private void setupKinect() {

		kinect = new Kinect();

		// kinect.stop();
		kinect.setColorResolution(640, 480);
		kinect.setDepthResolution(640, 480);

		kinect.start(Kinect.DEPTH | Kinect.COLOR | Kinect.SKELETON | Kinect.XYZ
				| Kinect.PLAYER_INDEX);

		// Espera para el encendido
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		kinect.setElevationAngle(0);
	}

	public void actualizar() {

		data = new SensorData(kinect);

		BufferedImage imagen = null;
		if (radioColor.isSelected()) {
			imagen = data.getImagenColor();
		}
		if (radioProfundidad.isSelected()) {
			imagen = data.getImagenProfundidad();
		}
		if (radioAmbos.isSelected()) {

		}

		labelPrincipalImagen.setIcon(new ImageIcon(imagen));
	}
}
