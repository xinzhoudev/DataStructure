package DataStructure.Module1_ArrayList;

import java.util.Iterator;

public class M1 {
    LinkedPositionalList<User> users;
    LinkedPositionalList<User> freeUsers;
    LinkedPositionalList<User> bankOfLosAngeles;
    public M1(){
        users = new LinkedPositionalList<>();
    }

    public void addUser(User u){
        if(!freeUsers.isEmpty()){
            User temp = freeUsers.first().getElement();
            u.ID = temp.ID;
            freeUsers.remove(freeUsers.first());
            users.addLast(temp);
        }else{
            User temp = users.last().getElement();
            u.ID = temp.ID + 1;
            users.addLast(u);  
        }
        insertionSort(users);
        insertionSort(freeUsers);
    }

    public void deleteUser(int ID){
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User temp = it.next();
            if(temp.ID == ID){
                it.remove();
                freeUsers.addLast(temp);
            }
        }
        insertionSort(freeUsers);
    }

    // Pay user to user(payerID, payeeID, amount);
    public void payUserToUser(int payerID, int payeeID, int amount){
        User payer = null, payee = null;
        for(User pay: users){
            if(pay.ID == payerID){
                payer = pay;
            }
            if(pay.ID == payeeID){
                payee = pay;
            }
            if(payer!=null && payee!=null) break;
        }
        payer.DepositAmount -= amount;
        payee.DepositAmount += amount;
    }

    // Merge Accounts;
    public void mergeAccounts(int ID1, int ID2){
        for(Position<User> pay1:users.positions()){
            for(Position<User> pay2:users.positions()){
                if((pay1.getElement().ID == ID1 && pay2.getElement().ID == ID2) || (pay1.getElement().ID == ID2 && pay2.getElement().ID == ID1)){
                    if(pay1.getElement().Address.equals(pay2.getElement().Address) && pay1.getElement().Name.equals(pay2.getElement().Name) && pay1.getElement().SSN.equals(pay2.getElement().SSN)){
                        if(pay1.getElement().ID < pay2.getElement().ID){
                            pay1.getElement().DepositAmount += pay2.getElement().DepositAmount;
                            users.remove(pay2);
                        }else{
                            pay2.getElement().DepositAmount += pay1.getElement().DepositAmount;
                            users.remove(pay1);
                        }
                        break;
                    }
                }
            }
        }
    }

    // Merge two banks: bank of Orange County and Bank of Los Angeles.
    public void mergeBanks(LinkedPositionalList<User> bankOfOrangeCounty, LinkedPositionalList<User> bankOfLosAngeles){
        for(Position<User> la: bankOfLosAngeles.positions()){
            if(!freeUsers.isEmpty()){
                la.getElement().ID = freeUsers.first().getElement().ID;
                freeUsers.remove(freeUsers.first());
            }else{
                la.getElement().ID = users.last().getElement().ID;
            }
            users.addLast(la.getElement());
        }
        insertionSort(users);
    }



    public static void insertionSort(PositionalList<User> list){
        Position<User> marker = list.first();
        while(marker != list.last()){
            Position<User> pivot = list.after(marker);
            User value = pivot.getElement();
            if(value.ID > marker.getElement().ID){
                marker = pivot;
            }else{
                Position<User> walk = marker;
                while(walk != list.first() && list.before(walk).getElement().ID > value.ID){
                    walk = list.before(walk);
                }
                list.remove(pivot);
                list.addBefore(walk, value);
            }
        }
    }


    public static void main(String[] args){
        M1 m = new M1();
        User user1 = new User(3000, "Xin", "Park Newport", "3029103", 1000);
        User user2 = new User(200, "Xin", "Park Newport", "3029103", 1000);
        User user3 = new User(100, "Xin", "Park Newport", "3029103", 1000);
        User user4 = new User(700, "Xin", "Park Newport", "3029103", 1000);
        m.users.addFirst(user1);
        m.users.addFirst(user2);
        m.users.addFirst(user3);
        m.users.addFirst(user4);
        insertionSort(m.users);
        Iterator<User> it = m.users.iterator();
        while(it.hasNext()){
            User temp = it.next();
            System.out.println(temp.ID);
        }
        System.out.println(m.users.size());
    }
}


class User{
    int ID;
    String Name;
    String Address;
    String SSN;
    int DepositAmount;
    public User(int ID, String Name, String Address, String SSN, int DepositAmount){
        this.ID = ID;
        this.Name = Name;
        this.Address = Address;
        this.SSN = SSN;
        this.DepositAmount = DepositAmount;
    }
}

