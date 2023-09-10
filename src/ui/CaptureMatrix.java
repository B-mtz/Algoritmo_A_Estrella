package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;

public class CaptureMatrix extends JFrame{
    private JPanel contentPane, contentMatrix,contentCenter,contentSouth, contentNorth, contentLeft, contentRight;
    private Font font = new Font("Cascadia Code",Font.BOLD,20);
    public ArrayList<JTextField> squares = new ArrayList<>();
    public ArrayList<JButton> btnsquares = new ArrayList<>();
    public JButton btnFill, btnConfirm, btnReset, btnNewMatrix;
    public int rows, columns;
    public JTable openSet, closSet;

    public CaptureMatrix(int rows, int columns){
        contentPane = new JPanel(new BorderLayout());
        this.setContentPane(contentPane);
        this.setSize(500,570);
        this.rows = rows;
        this.columns = columns;

        //Se crean los componentes de la interfaz
        fillMatrix();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    // Muestra una matriz para que el usuario rellene los valores desde la interfaz
    public void fillMatrix(){
            //Se crean paneles con layout para agregar componentes visuales de la interfaz
            contentNorth = new JPanel(new FlowLayout());
            contentNorth.setBorder(BorderFactory.createEmptyBorder(2, 5, 0, 5));
            contentCenter = new JPanel(new BorderLayout());
            contentCenter.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
            contentSouth = new JPanel(new FlowLayout());
            contentSouth.setBorder(BorderFactory.createEmptyBorder(0, 5, 20, 5));

            //Content North
            JLabel title  = new JLabel("Ingresa los valores de la matriz");
            title.setFont(font);
            title.setForeground(Color.ORANGE);
            contentNorth.add(title);

            //Content Center
            contentMatrix = new JPanel(new GridLayout(rows,columns));
            for (int i= 0; i<rows*columns;i++){
                JTextField a = new JTextField("");
                a.setBackground(Color.DARK_GRAY);
                a.setForeground(Color.ORANGE);
                a.setFont(font);
                a.setHorizontalAlignment(0);
                a.setSize(5,5);
                ((AbstractDocument) a.getDocument()).setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        if (fb.getDocument().getLength() + text.length() - length <= 1) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });
                contentMatrix.add(a);
                squares.add(a);
            }
            contentCenter.add(contentMatrix, BorderLayout.CENTER);

            //Content South
            btnFill = new JButton("Rellenar");
            btnFill.setBackground(Color.ORANGE);
            btnFill.setForeground(Color.BLACK);
            btnFill.setFont(font);
            contentSouth.add(btnFill);

            contentPane.add(contentNorth,BorderLayout.NORTH);
            contentPane.add(contentCenter,BorderLayout.CENTER);
            contentPane.add(contentSouth,BorderLayout.SOUTH);
    }

    //Muestra una matriz de botones que permite seleccionar el inicio y el fin
    public void getStartEnd(){
        contentNorth.removeAll();
        contentCenter.removeAll();
        contentMatrix.removeAll();
        contentSouth.removeAll();

        //Content North
        JLabel title  = new JLabel("Selecciona el inicio y el fin");
        title.setFont(font);
        title.setForeground(Color.ORANGE);
        contentNorth.add(title);

        //Content Center
        for (int i= 0; i<rows*columns;i++){
            JButton a = new JButton(squares.get(i).getText());
            a.setBackground(Color.DARK_GRAY);
            a.setForeground(Color.ORANGE);
            a.setFont(font);
            a.setHorizontalAlignment(0);
            a.setSize(5,5);
            contentMatrix.add(a);
            btnsquares.add(a);
        }
        contentCenter.add(contentMatrix, BorderLayout.CENTER);

        //Content South
        btnConfirm = new JButton("Confirmar");
        btnConfirm.setBackground(Color.ORANGE);
        btnConfirm.setForeground(Color.BLACK);
        btnConfirm.setFont(font);
        contentSouth.add(btnConfirm);

        btnReset = new JButton("Restablecer");
        btnReset.setBackground(Color.ORANGE);
        btnReset.setForeground(Color.BLACK);
        btnReset.setFont(font);
        contentSouth.add(btnReset);

        contentNorth.updateUI();
        contentCenter.updateUI();
        contentSouth.updateUI();
    }

    public void executeAlgoritm(){
        this.setSize(1280,550);
        contentNorth.removeAll();
        contentCenter.removeAll();
        contentSouth.removeAll();
        contentLeft = new JPanel(new BorderLayout());
        contentLeft.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 5));
        contentRight = new JPanel(new BorderLayout());
        contentRight.setBorder(BorderFactory.createEmptyBorder(0, 5, 15, 15));

        //Content North
        JLabel title  = new JLabel("Algoritmo n*");
        title.setFont(new Font("Cascadia Code",Font.BOLD,30));
        title.setForeground(Color.WHITE);
        contentNorth.add(title);

        //Content Center
        JLabel subTitle = new JLabel("Matriz");
        subTitle.setFont(font);
        subTitle.setForeground(Color.ORANGE);
        subTitle.setHorizontalAlignment(0);
        contentCenter.add(subTitle,BorderLayout.NORTH);
        contentCenter.add(contentMatrix,BorderLayout.CENTER);

        //Content South
        btnNewMatrix = new JButton("Nueva Matrix");
        btnNewMatrix.setBackground(Color.ORANGE);
        btnNewMatrix.setForeground(Color.BLACK);
        btnNewMatrix.setFont(font);
        contentSouth.add(btnNewMatrix);

        //Content Left
        JPanel topContentLeft = new JPanel(new FlowLayout());
        JLabel lbOpenSet  = new JLabel("OpenSet");
        lbOpenSet.setFont(font);
        lbOpenSet.setForeground(Color.ORANGE);
        topContentLeft.add(lbOpenSet);

        JPanel centerContentLeft = new JPanel(new FlowLayout());
        openSet = new JTable();
        DefaultTableModel openSetModel = new DefaultTableModel();
        String[] titles ={"Coordenada","Costo","Heuristica","Total","Origen"};
        openSetModel.setColumnIdentifiers(titles);
        openSet.setModel(openSetModel);
        JScrollPane scrollOpenSet = new JScrollPane(openSet);
        centerContentLeft.add(scrollOpenSet);
        contentLeft.add(topContentLeft,BorderLayout.NORTH);
        contentLeft.add(centerContentLeft,BorderLayout.CENTER);

        //Content Right
        JPanel topContentRigth = new JPanel(new FlowLayout());
        JLabel lbCloseSet  = new JLabel("CloseSet");
        lbCloseSet.setFont(font);
        lbCloseSet.setForeground(Color.ORANGE);
        topContentRigth.add(lbCloseSet);

        JPanel centerContentRight = new JPanel(new FlowLayout());
        closSet = new JTable();
        DefaultTableModel closeSetModel = new DefaultTableModel();
        closeSetModel.setColumnIdentifiers(titles);
        closSet.setModel(closeSetModel);
        JScrollPane scrollCloseSet = new JScrollPane(closSet);
        centerContentRight.add(scrollCloseSet);
        contentRight.add(topContentRigth, BorderLayout.NORTH);
        contentRight.add(centerContentRight, BorderLayout.CENTER);

        contentNorth.updateUI();
        contentSouth.updateUI();
        contentCenter.updateUI();
        contentPane.add(contentLeft, BorderLayout.WEST);
        contentPane.add(contentRight, BorderLayout.EAST);

        this.setLocationRelativeTo(null);
    }
}