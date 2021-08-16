import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Form extends JFrame {

    private JFrame form;
    private JPanel leftP, rightP;
    private JLabel labelLP, labelRP, picL, picR;
    private int xform, yform, widthform, heightform, xleftp, yleftp, widthleftp, heightleftp, xrightp, yrightp, widthrightp, heightrightp;
    private String[][] leftphoto, rightphoto, lphoto, lphoto1;
    private ImageIcon icon;
    private File mypathl, mypathr, mypathlt;

    public Form() throws InterruptedException {

        xform = 0;
        yform = 0;
        widthform = 1600;
        heightform = 600;
        xleftp = 0;
        yleftp = 0;
        widthleftp = 800;
        heightleftp = 600;
        xrightp = 800;
        yrightp = 0;
        widthrightp = 800;
        heightrightp = 600;


        form = new JFrame("ScrollingPhotos");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setBounds(xform, yform, widthform, heightform);
        form.setLayout(null);

        leftP = new JPanel();
        leftP.setBounds(xleftp, yleftp, widthleftp, heightleftp);
        leftP.setLayout(null);
        leftP.setVisible(true);

        rightP = new JPanel();
        rightP.setBounds(xrightp, yrightp, widthrightp, heightrightp);
        rightP.setLayout(null);
        rightP.setVisible(true);

        labelLP = new JLabel("LEFT");
        labelLP.setForeground(Color.YELLOW);
        labelLP.setBounds(0, 500, 800, 100);
        labelLP.setHorizontalAlignment(0);
        labelLP.setVisible(true);

        picL = new JLabel();
        picL.setBounds(0, 0, 800, 600);
        picL.setVisible(true);

        labelRP = new JLabel("RIGHT");
        labelRP.setOpaque(true);
        labelRP.setForeground(Color.YELLOW);
        labelRP.setBackground(Color.RED);
        labelRP.setBounds(0, 500, 800, 100);
        labelRP.setHorizontalAlignment(0);
        labelRP.setVisible(true);




        String mypathL = "./res/left";


        lphoto = ReadPhotosLeft();
        String mypathtl;

        Random random = new Random();
        int count = 0;
        while (count != 9) {
            System.out.println(lphoto.length);
            int i = random.nextInt(lphoto.length);
            System.out.println(i);
            labelLP.setText(lphoto[i][0]);
            mypathtl = mypathL + "/" + lphoto[i][0];
            System.out.println(lphoto[i].length);
            int j = 0;
            while (j == 0) j = random.nextInt(lphoto[i].length);
            System.out.println(j);
            icon = new ImageIcon(mypathtl + "/" + lphoto[i][j]);

            int nw = icon.getIconWidth();
            int nh = icon.getIconHeight();

            if (icon.getIconWidth() > picL.getWidth()) {
                nw = picL.getWidth();
                nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
            }

            if (nh > picL.getHeight()) {
                nh = picL.getHeight();
                nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
            }

            ImageIcon newImc = new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
            picL.setIcon(newImc);
            picL.setHorizontalAlignment(0);

            leftP.add(picL);
            picL.add(labelLP);
            rightP.add(labelRP);
            form.getContentPane().add(leftP);
            form.getContentPane().add(rightP);
            form.setVisible(true);

            TimeUnit.SECONDS.sleep(2);
            count++;

        }
    }

    public String[][] ReadPhotosLeft(){

        String mypath = "./res/left";
        String mypathtemp;
        mypathl = new File(mypath);

        File[] listOfFiles = mypathl.listFiles();

        assert listOfFiles != null;
        leftphoto = new String[listOfFiles.length][];

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                System.out.println("papka " + listOfFiles[i].getName());
                mypathtemp = mypath + "/" + listOfFiles[i].getName();
                System.out.println(mypathtemp);
                mypathlt = new File(mypathtemp);
                File[] listOfFiles1 = mypathlt.listFiles();

                assert listOfFiles1 != null;
                leftphoto[i] = new String[listOfFiles1.length + 1];
                leftphoto[i][0] = listOfFiles[i].getName();

                for (int j = 0; j < listOfFiles1.length; j++) {
                    if (listOfFiles1[j].isFile()) {
                        System.out.println("File " + listOfFiles1[j].getName());
                        leftphoto[i][j+1] = listOfFiles1[j].getName();

                    }
                }
            }
        }

        for (int i = 0; i < leftphoto.length; i++){
            for (int j = 0; j < leftphoto[i].length; j++){
                System.out.print(leftphoto[i][j] + " ");
            }
            System.out.println();
        }



        return leftphoto;

    }

    public String[][] ReadPhotosRight(){

        mypathr = new File("./res/right");


        return null;

    }
    
}
