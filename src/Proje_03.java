

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Proje_03 {

/*
     Bu proje %90 bir onceki projeden alinmistir... Ufak degisiklikler mevcuttur.. ClASS yapisinin sizlere sagladigi kolayligi
     bu proje ile daha iyi anlayacaksiniz... Bu class gerekli kodlamalar yapildiginda video da gorulen sonucun aynisini verecek
     sekilde duzenlenmelidir...
        - not :  Tek degisiklik = Bir onceki proje de her kullanicinin 1 tek hesap numarasi vardi... Burada her musteriye 2 adet
                 hesap tanimlanmasini istiyoruz...
              :  Ayrica musteriye ait hesaplar listelenirken gecen hafta yapilan hatayi giderecek sekilde her kullanicinin kendi banka
              hesaplarinin listelenmesini saglayiniz...
     * Bu Class da KIRMIZI olarak gorulen kodlar sizin tarafinizdan eklenecek kodlar ile calisir hale gelecekler...
     * Sizlerden asagida belirtilen-kullanilmis Class lari ayri ayri olusturmanizi ve asagida tanimli methodlarin icini doldurmanizi bekliyoruz...
     */


    public static void main(String[] args) {

        // Scanner Class tanimlayin...
        Scanner scan = new Scanner(System.in);

        // Banka Islemleri (Actions) Class indan bir liste olusturun...4 adet islem tanimlayin "Para_Yatir", "Para_Cek", "Transfer", "Cikis"...

        Actions actn1 = new Actions("Para_Yatir");
        Actions actn2 = new Actions("Para_Cek");
        Actions actn3 = new Actions("Transfer");
        Actions actn4 = new Actions("Cikis");

        List<Actions> actions = new ArrayList<>(Arrays.asList(actn1, actn2, actn3, actn4));


        // Her musteri (Customer) icin 2 adet hesap (Account) tanimlayin.. Hesap sinifi hesap numarasi ve hesaptaki para miktarini icermeli...

        //musteri 1 icin 2 adet hesap
        Account ac11 = new Account();
        ac11.number = "1234";
        ac11.balance = 0;
        Account ac12 = new Account();
        ac12.number = "4321";
        ac12.balance = 0;

        //musteri 2 icin 2 adet hesap
        Account ac21 = new Account();
        ac21.number = "5678";
        ac21.balance = 0;
        Account ac22 = new Account();
        ac22.number = "8765";
        ac22.balance = 0;

        //musteri 3 icin 2 adet hesap
        Account ac31 = new Account();
        ac31.number = "9999";
        ac31.balance = 0;
        Account ac32 = new Account();
        ac32.number = "8888";
        ac32.balance = 0;


        // Banka musterilerini Customer sinifindan objeler olusturarak liste olarak tanimlayin...Customer sinifinda bir musteriye ait
        // username, password ve birden fazla hesap (Account) bulunmalidir...Buna gore bu musterileri burada tanimlayin...

        //Musteri 1
        Customer customer1 = new Customer();
        customer1.username = "user1";
        customer1.password = "password1";
        customer1.accounts.add(ac11);
        customer1.accounts.add(ac12);

        //Musteri 2
        Customer customer2 = new Customer();
        customer2.username = "user2";
        customer2.password = "password2";
        customer2.accounts.add(ac21);
        customer2.accounts.add(ac22);

        //Musteri 3
        Customer customer3 = new Customer();
        customer3.username = "user3";
        customer3.password = "password3";
        customer3.accounts.add(ac31);
        customer3.accounts.add(ac32);

        //Musteriler listeye eklendi
        ArrayList<Customer> customers = new ArrayList<>(Arrays.asList(customer1, customer2, customer3));


        // Asagidaki kodda KIRMIZI olarak gorulen yerler sizin olustiracaginiz siniflar ve yukarida tanimlayacaginiz objeler ile
        // normal olarak calisacaktir...
        Customer aktifKullanici;
        Account secilenHesap;

        while (true) {
            System.out.println("Please enter your username: ");
            String username = scan.nextLine();
            System.out.println("Please enter your password: ");
            String password = scan.nextLine();

            // -> Bu methodu asagida hangi kullanicinin giris yaptigini belirlemek maksadiyla kullanacaksiniz, olusturun...
            aktifKullanici = confirmUsernameAndPassword(customers, username, password);

            if (aktifKullanici != null) {
                System.out.println("Basarili bir sekilde giris yaptiniz");
                break;
            }
            System.out.println("Sistemde kayitli boyle bir kullanici bulunamadi.. Tekrar deneyin");
        }

        // Actions starts here
        while (true) {
            System.out.println("Yapmak istediginiz islemi seciniz...");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println(actions.get(i).description + " - " + (i + 1));
            }
            int userChoice = scan.nextInt();

            switch (userChoice) {

                case 1: {

                    while (true) {
                        // Burada artik sadece o kullaniciya ait banka hesaplari listeleniyor... CLASS kullanmainin faydalari...
                        System.out.println("Lutfen para yatirmak istediginiz hesap numarasini giriniz...: ");
                        for (Account account : aktifKullanici.accounts) {
                            System.out.println(account.number);
                        }
                        String secim = scan.next();
                        // confirmAccountNumber() methodunu kullanicinin dogru hesap bilgileri girdigini teyit etmek amacli kullanacaksiniz..
                        secilenHesap = confirmAccountNumber(aktifKullanici, secim);
                        if (secilenHesap == null) {
                            System.out.println("Hatali hesap numarasi girdiniz...");
                            continue;
                        }
                        System.out.println("Lutfen yatirmak istediginiz para miktarini giriniz...: ");
                        int yatirilicakPara = scan.nextInt();
                        secilenHesap.balance += yatirilicakPara;
                        System.out.println("Güncel Bakiyeniz :" + secilenHesap.balance);
                        break;
                    }
                    break;
                }

                case 2: {

                    while (true) {
                        // Burada artik sadece o kullaniciya ait banka hesaplari listeleniyor... CLASS kullanmainin faydalari...
                        System.out.println("Lutfen para cekmek istediginiz hesap numarasini giriniz...: ");
                        for (Account account : aktifKullanici.accounts) {
                            System.out.println(account.number);
                        }
                        String secim = scan.next();
                        // confirmAccountNumber() methodunu kullanicinin dogru hesap bilgileri girdigini teyit etmek amacli kullanacaksiniz..
                        secilenHesap = confirmAccountNumber(aktifKullanici, secim);
                        if (secilenHesap == null) {
                            System.out.println("Hatali hesap numarasi girdiniz...");
                            continue;
                        }
                        System.out.println("Lutfen cekmek istediginiz para miktarini giriniz...: ");
                        int cekilecekPara = scan.nextInt();
                        // withdraw() methodunu kullanicinin hesabinda yeterli bakiye varsa bakiyesinden paranin dusulmesi,
                        // Yeterli bakiye yoksa kullanicinin uyarilmasi maksadiyla kullanacaksiniz...Olusturun
                        if (!withdraw(secilenHesap, cekilecekPara)) {
                            System.out.println("Hesabınızda yeterli bakiye yok tekrar deneyin");
                            continue;
                        }
                        break;
                    }
                    break;
                }

                case 3: {
                    System.out.println("Bu islemi su an gerceklestiremiyoruz...");
                    break;
                }

                case 4:
                    System.exit(1);

                default: {
                    System.out.println("Basarili bir secim yapmadiniz...");
                }

            }
        }

    }

    public static Customer confirmUsernameAndPassword(List<Customer> musteriler, String username, String password) {

        Customer a = null;
        for (Customer c : musteriler) {
            if (c.username.equals(username) && c.password.equals(password))
                a = c;
        }

        return a;
    }

    public static Account confirmAccountNumber(Customer currentUser, String chosenAccount) {

        Account secilenHesap = null;
        for (Account a : currentUser.accounts) {
            if (a.number.equals(chosenAccount))
                secilenHesap = a;
        }
        return secilenHesap;
    }

    public static boolean withdraw(Account chosenAccount, int amountToWithdraw) {


        if (chosenAccount.balance >= amountToWithdraw) {
            System.out.println("Lutfen paranizi aliniz. Hesabınızda " +
                    ((chosenAccount.balance) - amountToWithdraw) + " euro kaldı.");
            chosenAccount.balance -= amountToWithdraw;
            return true;
        } else
            return false;
    }


}
