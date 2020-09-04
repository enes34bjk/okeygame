import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class okey {
    private int[] taslar = new int[106]; //Array for storing all the numbers

    private int[] o1taslar;//
    private int[] o2taslar;//Arrays for player's numbers
    private int[] o3taslar;//
    private int[] o4taslar;//
    int[][] players = {o1taslar, o2taslar, o3taslar, o4taslar};
    private int okeytasi = 0;

    public static void main(String Args[]) {
        okey oyun = new okey();
        oyun.oyunOyna();
    }

    //Method for shuffling the numbers and giving to the players
    public void oyunOyna() {


        /////////////// Putting all numbers to the array////////////////
        int y = 0;
        int a = 0;
        while (y <= 105) {
            taslar[y] = a;
            taslar[y + 1] = a;
            a = a + 1;
            y = y + 2;
        }
        /////////////////////////////

        tasKaristir();

        okeySec();

        taslariDagit();

        sonuclariGöster();

    }

    public void sonuclariGöster() {
        //Showing the results
        double[] sapmalar = new double[4];
        System.out.print("Oyuncu Eli " + Arrays.toString(players[0]) + " Standart Sapması: ");
        sapmalar[0] = renklereAyir(players[0]);
        System.out.println(sapmalar[0]);
        System.out.print("Oyuncu Eli " + Arrays.toString(players[1]) + " Standart Sapması: ");
        sapmalar[1] = renklereAyir(players[1]);
        System.out.println(sapmalar[1]);
        System.out.print("Oyuncu Eli " + Arrays.toString(players[2]) + " Standart Sapması: ");
        sapmalar[2] = renklereAyir(players[2]);
        System.out.println(sapmalar[2]);
        System.out.print("Oyuncu Eli " + Arrays.toString(players[3]) + " Standart Sapması: ");
        sapmalar[3] = renklereAyir(players[3]);
        System.out.println(sapmalar[3]);
        int bitmeyeenyakınel = enkucukbul(sapmalar);
        System.out.println("");
        System.out.println("Bitmeye en yakın el: " + Arrays.toString(players[bitmeyeenyakınel]));
    }

    public void tasKaristir() {

        //Shuffling the numbers before distributing to the players
        Random rand = new Random();
        for (int i = 0; i < taslar.length; i++) {
            int randomIndexToSwap = rand.nextInt(taslar.length);
            int temp = taslar[randomIndexToSwap];
            taslar[randomIndexToSwap] = taslar[i];
            taslar[i] = temp;
        }
        /////////////////////////////////
    }

    public void okeySec() {
        //Choosing the random number
        Random rand = new Random();

        int randomPosition = rand.nextInt(taslar.length);
        int randomNumber = taslar[randomPosition];

        while (randomNumber == 52) { //if the random number is 52
            randomPosition = rand.nextInt(taslar.length);
            randomNumber = taslar[randomPosition];
        }
        if (randomNumber == 12 || randomNumber == 25 || randomNumber == 38 || randomNumber == 51) { //if needed to go to beginning number
            randomNumber = randomNumber - 12;
        } else {
            randomNumber += 1;
        }
        okeytasi = randomNumber;

        ///////////////////////////
    }

    public void taslariDagit() {
        //distrubuting the numbers among players
        Random rand = new Random();
        for (int i = 0; i < players.length; i++) {//shuffling the players to get random person for 15 numbers
            int randomIndexToSwap = rand.nextInt(players.length);
            int temp[] = players[randomIndexToSwap];
            players[randomIndexToSwap] = players[i];
            players[i] = temp;
        }
        int playerindex = 0;
        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new int[15];
            } else
                players[i] = new int[14];
        }
        for (int i = 0; i < 57; i++) {
            if (i < 15) {
                players[playerindex][i] = taslar[i];
            } else if (i < 29) {
                players[playerindex + 1][i - 15] = taslar[i];
            } else if (i < 43) {
                players[playerindex + 2][i - 29] = taslar[i];
            } else {
                players[playerindex + 3][i - 43] = taslar[i];
            }

        }
        ////////////////////////////////
    }

    public double standartSapma(ArrayList<Integer> sayılar) {
        double toplam = 0.0, standartsapma = 0.0;
        int uzunluk;
        while (sayılar.contains(okeytasi)){
            sayılar.remove(sayılar.indexOf(okeytasi));
            toplam-=1;
        }
        while (sayılar.contains(52)){
            sayılar.remove(sayılar.indexOf(52));
            sayılar.add(okeytasi);
        }

        uzunluk = sayılar.size();
        for (int i = 0; i < sayılar.size(); i++) {

            toplam += sayılar.get(i);

        }
        double ortalama = toplam / uzunluk;

        for (int i = 0; i < sayılar.size(); i++) {
            standartsapma += Math.pow(sayılar.get(i) - ortalama, 2);

        }
        double sonuc=Math.sqrt(standartsapma / uzunluk);
        return sonuc;
    }

    public double renklereAyir(int[] istaka) {
        ArrayList<Integer> istakka= new ArrayList();
        for (int i:istaka){
            istakka.add(i);
        }
        for (int i=0;i<13;i++){
            int i1=i+13;
            int i2=i+26;
            int i3=i+39;
            if ((istakka.contains(i)&&istakka.contains(i1)&&istakka.contains(i2)&&istakka.contains(i3))){

                istakka.remove(istakka.indexOf(i));
                istakka.remove(istakka.indexOf(i1));
                istakka.remove(istakka.indexOf(i2));
                istakka.remove(istakka.indexOf(i3));
            }
            else if ((istakka.contains(i)&&istakka.contains(i1)&&istakka.contains(i2))){
                istakka.remove(istakka.indexOf(i));
                istakka.remove(istakka.indexOf(i1));
                istakka.remove(istakka.indexOf(i2));

            }else if ((istakka.contains(i)&&istakka.contains(i1)&&istakka.contains(i3))){
                istakka.remove(istakka.indexOf(i));
                istakka.remove(istakka.indexOf(i1));
                istakka.remove(istakka.indexOf(i3));

            }else if ((istakka.contains(i)&&istakka.contains(i2)&&istakka.contains(i3))){
                istakka.remove(istakka.indexOf(i));
                istakka.remove(istakka.indexOf(i2));
                istakka.remove(istakka.indexOf(i3));

            }else if ((istakka.contains(i1)&&istakka.contains(i2)&&istakka.contains(i3))){
                istakka.remove(istakka.indexOf(i1));
                istakka.remove(istakka.indexOf(i2));
                istakka.remove(istakka.indexOf(i3));

            }
        }
        ArrayList<Integer> sari = new ArrayList();
        ArrayList<Integer> mavi = new ArrayList();
        ArrayList<Integer> siyah = new ArrayList();
        ArrayList<Integer> kirmizi = new ArrayList();
        for (int sayi=0;sayi<istakka.size();sayi++) {
            if (istakka.get(sayi) < 13) {
                sari.add(istakka.get(sayi));
            } else if (istakka.get(sayi) < 26) {
                mavi.add(istakka.get(sayi));
            } else if (istakka.get(sayi) < 39) {
                siyah.add(istakka.get(sayi));
            } else if (istakka.get(sayi) < 52) {
                kirmizi.add(istakka.get(sayi));
            }
        }
        return standartSapma(sari) + standartSapma(mavi) + standartSapma(siyah) + standartSapma(kirmizi);
    }

    public int enkucukbul(double[] arr1) {
        int index = 0;
        double min = arr1[index];

        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] < min) {
                min = arr1[i];
                index = i;
            }

        }
        return index;
    }
}
