import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
class triomino extends JFrame {
    private String[][] nBoard;   
    static int nSize;
    static int holeA;
    static int holeB;

    int counter = 1;
    public static void setValues(String N, String A, String B){
            nSize = Integer.parseInt(N);
            holeA = Integer.parseInt(A);
            holeB = Integer.parseInt(B);
    }
    public static void main(String[] args) {
        triomino tri = new triomino();
        int gridSize = 16;

        JFrame input = new JFrame();
        input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        input.setSize(500, 500);

        input.getContentPane().setLayout(null);
        JTextField textField_0 = new JTextField();
        textField_0.setBounds(175, 50, 100, 15);
        input.getContentPane().add(textField_0);
        textField_0.setColumns(10);

        JLabel lblSize = new JLabel("Size(2^n * 2^n) n:");
        lblSize.setBounds(65, 31, 100, 50);
        input.getContentPane().add(lblSize);

        JTextField textField_1 = new JTextField();
        textField_1.setBounds(175, 110, 86, 20);
        input.getContentPane().add(textField_1);

        JLabel lblRemoveHole_A = new JLabel("Removed A (A,B):");
        lblRemoveHole_A.setBounds(70, 112, 247, 17);
        input.getContentPane().add(lblRemoveHole_A);
        
        JTextField textField_2 = new JTextField();
        textField_2.setBounds(175, 160, 86, 20);
        input.getContentPane().add(textField_2);

        JLabel lblRemoveHole_B = new JLabel("Removed B (A,B):");
        lblRemoveHole_B.setBounds(70, 162, 247, 17);
        input.getContentPane().add(lblRemoveHole_B);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(312, 387, 89, 23);
        input.getContentPane().add(btnSubmit);
        ActionListener submitInput = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getSource() == btnSubmit){
                    setValues(textField_0.getText(), textField_1.getText(), textField_2.getText());
                    input.dispose();
                } 
            }
        };
        btnSubmit.addActionListener(submitInput);
        input.setVisible(true);
        //Press Enter to Start Program 
        Scanner myObj = new Scanner(System.in); 
        String userName = myObj.nextLine();
        System.out.println(nSize + " " + holeA + " " + holeB);
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        gridSize = (int) Math.pow(2, nSize);
        frame.setLayout(new GridLayout(gridSize, gridSize));
        ActionListener button = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e){
			JButton o =	((JButton) e.getSource());
            final String tempRemoveHole = (o.getText());
            o.setBackground(Color.BLACK);
			}
		};
        JButton[][] arrButtons = new JButton[gridSize][gridSize];
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                arrButtons[i][j] = new JButton(i + "," + j);
                frame.add(arrButtons[i][j]);
                arrButtons[i][j].addActionListener(button);
            }
        }
       



        arrButtons[holeA][holeB].setBackground(Color.BLACK);
        frame.setVisible(true);
        tri.tri(nSize,holeA,holeB,5,6,arrButtons);
    }

    public int tri(int n, int a, int b, int i, int j, JButton jboard[][]){
        int nSqr = (int) Math.pow(2, n);
        String[][] board = new String[nSqr][nSqr];
        //
        for (int k = 0; k < nSqr; k++) {
            for (int m = 0; m < nSqr; m++) {
                board[k][m] = "O";
            }
        }
        board[a][b] = "X";
        placeTri(0, 0, i, j, board, nSqr, 0, jboard);
        return 0;
    }
    public int placeTri(int a, int b, int i, int j, String board[][], int nSqr, int s, JButton buttonArr[][]){
        int c = 0, d = 0; 
        float hue1 = (float) Math.random();
        int rgb2 = Color.HSBtoRGB(hue1,0.5f,0.5f);
        Color color2 = new Color(rgb2);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        if(nSqr == 2){
            for(int ii = 0; ii < nSqr; ii++){
                for(int jj = 0; jj < nSqr; jj++){
                    if(board[a+ii][b+jj].equals("O")){
                        board[a+ii][b+jj] = "X";
                        buttonArr[a+ii][b+jj].setBackground(color2);
                        
                    }
                }
            }
            return 0;
        }
        for (int ii = a; ii < a + nSqr; ii++ ){
            for(int jj = b; jj < b + nSqr; jj++){
                if(!board[ii][jj].equals("O")){
                    c = ii;
                    d = jj;
                }
            }
        }
        //Top Left Quadrant 
        if(c < nSqr/2 + a && d < b + nSqr/2){
            board[a + nSqr/2][b + (nSqr/2)-1] = "X"; //Bottom left square
            board[a + nSqr/2][b + nSqr/2] = "X"; //Bottom Right square
            board[a + (nSqr/2)-1][b + nSqr/2] = "X"; // Top Right Square
            float hue = (float) Math.random();
            int rgb = Color.HSBtoRGB(hue,0.5f,0.5f);
            Color color = new Color(rgb);
            buttonArr[a + nSqr/2][b + (nSqr/2)-1].setBackground(color);
            buttonArr[a + nSqr/2][b + nSqr/2].setBackground(color);
            buttonArr[a + (nSqr/2)-1][b + nSqr/2].setBackground(color);
            

        }
        //Top Right Quardrant
        else if(c < a + nSqr/2 && d >= b + nSqr/2){
            board[a + (nSqr/2)-1][b + (nSqr/2)-1] = "X"; //Top left square
            board[a + nSqr/2][(b + nSqr/2)-1] = "X"; //Bottom left square
            board[a + nSqr/2][b + nSqr/2] = "X"; //Bottom Right square
            float hue = (float) Math.random();
            int rgb = Color.HSBtoRGB(hue,0.5f,0.5f);
            Color color = new Color(rgb);
            buttonArr[a + (nSqr/2)-1][b + (nSqr/2)-1].setBackground(color);
            buttonArr[a + nSqr/2][(b + nSqr/2)-1].setBackground(color);
            buttonArr[a + nSqr/2][b + nSqr/2].setBackground(color);
        }
        //Bottom Left Quardrant
        else if(c >= a + nSqr/2 && d < b + nSqr/2){
            board[a + (nSqr/2)-1][b + (nSqr/2)-1] = "X"; //Top left square
            board[a + (nSqr/2)-1][b + nSqr/2] = "X"; // Top Right Square
            board[a + nSqr/2][b + nSqr/2] = "X"; //Bottom Right square
            float hue = (float) Math.random();
            int rgb = Color.HSBtoRGB(hue,0.5f,0.5f);
            Color color = new Color(rgb);
            buttonArr[a + (nSqr/2)-1][b + (nSqr/2)-1].setBackground(color);
            buttonArr[a + (nSqr/2)-1][b + nSqr/2].setBackground(color);
            buttonArr[a + nSqr/2][b + nSqr/2].setBackground(color);
        }
        //Bottom Right Quadrant
        else if(c >= a + nSqr/2 && d >= b + nSqr/2){
            board[a + (nSqr/2)-1][b + (nSqr/2)-1] = "X"; //Top left square
            board[a + (nSqr/2)-1][b + nSqr/2] = "X"; // Top Right Square
            board[a + nSqr/2][b + (nSqr/2)-1] = "X"; //Bottom left square
            float hue = (float) Math.random();
            int rgb = Color.HSBtoRGB(hue,0.5f,0.5f);
            Color color = new Color(rgb);
            buttonArr[a + (nSqr/2)-1][b + (nSqr/2)-1].setBackground(color);
            buttonArr[a + (nSqr/2)-1][b + nSqr/2].setBackground(color);
            buttonArr[a + nSqr/2][b + (nSqr/2)-1].setBackground(color);
        }
        placeTri(a, b + nSqr/2, i, j, board, nSqr/2, s, buttonArr);
        placeTri(a, b, i, j, board, nSqr/2, s, buttonArr);
        placeTri(a + nSqr/2, b, i, j, board, nSqr/2, s, buttonArr);
        placeTri(a + nSqr/2, b + nSqr/2, i, j, board, nSqr/2, s, buttonArr);
        //Bottom Square
        return 0;
    }
    //Prints out the 2D Matrix 
    public void placeAllTri(int a, int b, String board[][], int nSqr){
        System.out.println("Step " + counter);
        counter++;
        System.out.print("  ");
        for(int im = 0; im < nSqr; im++){
            System.out.print(im + " ");
        }
        System.out.println();
        for (int k = 0; k < nSqr; k++) {
            System.out.print(k + " ");
            for (int m = 0; m < nSqr; m++) {
                System.out.print(board[k][m]+" ");
            }
            System.out.println();
        }
    }

}