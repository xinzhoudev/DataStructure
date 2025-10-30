package DataStructure.Module1_ArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


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
    // the user without id number. and the number of the user will increase by itself.
    public User(String Name, String Address, String SSN, int DepositAmount){
        this.ID = 1;
        this.Name = Name;
        this.Address = Address;
        this.SSN = SSN;
        this.DepositAmount = DepositAmount;
    }
}

class task{
    // task-1: Module the list of users as a linked list where each account is a node in the list. Users must be sorted by their ID in the linkedlist.
    LinkedPositionalList<User> users;
    LinkedPositionalList<User> freeUsers;
    LinkedPositionalList<User> bankOfLosAngeles;
    LinkedPositionalList<User> freeUsersBankOfLosAngeles;
    public task(){
        users = new LinkedPositionalList<>();
        freeUsers = new LinkedPositionalList<>();
        bankOfLosAngeles = new LinkedPositionalList<>();
        freeUsersBankOfLosAngeles = new LinkedPositionalList<>();
    }

    // task-2: Write a method addUser(user) that adds a new user. 
    public void addUser(User u){
        if(!freeUsers.isEmpty()){
            User temp = freeUsers.first().getElement();
            u.ID = temp.ID;
            freeUsers.remove(freeUsers.first());
            users.addLast(u);
        }else{
            if(users.isEmpty()){
                users.addLast(u);
            }else{
                // sort again to guarantee that the users are valid.
                User temp = users.last().getElement();
                u.ID = temp.ID + 1;
                users.addLast(u);  
            }
        }
        insertionSort(users);
        insertionSort(freeUsers);
    }

    // add users for Los Angeles Bank
    public void addLAUsers(User u){
        if(!freeUsersBankOfLosAngeles.isEmpty()){
            User temp = freeUsersBankOfLosAngeles.first().getElement();
            u.ID = temp.ID;
            freeUsersBankOfLosAngeles.remove(freeUsersBankOfLosAngeles.first());
            bankOfLosAngeles.addLast(u);
        }else{
            if(bankOfLosAngeles.isEmpty()){
                bankOfLosAngeles.addLast(u);
            }else{
                // sort again to guarantee that the users are valid.
                User temp = bankOfLosAngeles.last().getElement();
                u.ID = temp.ID + 1;
                bankOfLosAngeles.addLast(u);  
            }
        }
        insertionSort(bankOfLosAngeles);
        insertionSort(freeUsersBankOfLosAngeles);
    }


    // Task-3: Write a method deleteUser(ID) that deletes an existing user. Free up the unique ID while deleting the user.
    public void deleteUser(int ID){
        Iterator<User> it = users.iterator();
        boolean flg = false;
        while(it.hasNext()){
            User temp = it.next();
            if(temp.ID == ID){
                it.remove();
                freeUsers.addLast(temp);
                flg = true;
            }
        }
        if(!flg) {
            System.out.println("Invalid ID.");
            return;
        }
        insertionSort(freeUsers);
    }

    public void deleteUserLosAngeles(int ID){
        Iterator<User> it = bankOfLosAngeles.iterator();
        boolean flg = false;
        while(it.hasNext()){
            User temp = it.next();
            if(temp.ID == ID){
                it.remove();
                freeUsersBankOfLosAngeles.addLast(temp);
                flg = true;
            }
        }
        if(!flg) {
            System.out.println("Invalid ID.");
            return;
        }
        insertionSort(freeUsersBankOfLosAngeles);
    }


    // Task-4: Pay user to user(payerID, payeeID, amount);
    public void payUserToUser(int payerID, int payeeID, int amount){
        if(amount <= 0){
            System.out.println("It cannot transfer negative or zero amount to other account.");
        }
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
        if(payer == null || payee == null) {
            System.out.println("Invalid ID, please check if it exists.");
            return;
        }
        if(payer.DepositAmount < amount){
            System.out.println("Insufficient money, please to get more money and check it again.");
            return;
        }
        payer.DepositAmount -= amount;
        payee.DepositAmount += amount;
    }


    // Task-5: get median of all the account IDs.
    // explain.
    public double getMedianID(){
        insertionSort(users);
        int size = users.size(), index = 0;
        double median = 0;
        Iterator<User> it = users.iterator();
        if(size == 1) return (double)it.next().ID;
        if(size%2 == 0){
            while(index != size/2 - 1){
                index++;
                it.next(); 
            }
            median = ((double)it.next().ID + (double)it.next().ID)/2;
        }else{
            while(index != size/2){
                index++;
                it.next();
            }
            median = (double)it.next().ID;
        }
        return median;
    }

    // Merge Accounts;
    public void mergeAccounts(int ID1, int ID2){
        List<Position<User>> removeId = new ArrayList<>();
        outer:
        for(Position<User> pay1:users.positions()){
            for(Position<User> pay2:users.positions()){
                // judge whether the two persons are the same people.
                if((pay1.getElement().ID == ID1 && pay2.getElement().ID == ID2) || (pay1.getElement().ID == ID2 && pay2.getElement().ID == ID1)){
                    if(pay1.getElement().Address.equals(pay2.getElement().Address) && pay1.getElement().Name.equals(pay2.getElement().Name) && pay1.getElement().SSN.equals(pay2.getElement().SSN)){
                        if(pay1.getElement().ID < pay2.getElement().ID){
                            pay1.getElement().DepositAmount += pay2.getElement().DepositAmount;
                            removeId.add(removeId.size(), pay2);
                            freeUsers.add(pay2.getElement());
                        }else{
                            pay2.getElement().DepositAmount += pay1.getElement().DepositAmount;
                            removeId.add(removeId.size(), pay1);
                            freeUsers.add(pay1.getElement());
                        }
                        break outer;
                    }
                }
            }
        }
        for(int i = removeId.size()-1; i >= 0; i--){
            // User temp  = removeId.get(i).getElement();
            // System.out.println(temp.ID + " !!! " + temp.Address + " " + temp.DepositAmount);
            users.remove(removeId.get(i));
        }
    }



    // Task 7: Merge two banks: bank of Orange County and Bank of Los Angeles.
    public void mergeBanks(LinkedPositionalList<User> bankOfOrangeCounty, LinkedPositionalList<User> bankOfLosAngeles){
        Set<Integer> set = new HashSet<>();
        for(Position<User> oc:bankOfOrangeCounty.positions()){
            set.add(oc.getElement().ID);
        }
        for(Position<User> la: bankOfLosAngeles.positions()){
            // if the oc bank has the id of la bank, create a new id.
            System.out.println(la.getElement().ID + " " + freeUsers.size());
            if(!set.contains(la.getElement().ID)){
                if(!freeUsers.isEmpty()){
                    la.getElement().ID = freeUsers.first().getElement().ID;
                    freeUsers.remove(freeUsers.first());
                }else{
                    la.getElement().ID = users.last().getElement().ID + 1;
                }
                users.addLast(la.getElement());
            }else{
                la.getElement().ID = users.last().getElement().ID + 1;
                users.addLast(la.getElement());
            }
            insertionSort(users);
        }
    }


    public void insertionSort(PositionalList<User> list){
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



}




public class M1 {
    

    public static void main(String[] args){
        task m = new task();
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Xiao Meng", "Shanghai Yangpu", "3335", 10000));
        m.addUser(new User("Jiang Xiao", "Shanghai Yangpu", "12111455", 10000));
        m.addUser(new User("Meng Fan", "Shanghai Yangpu", "11232255", 10000));
        m.addUser(new User("Chen Zi Ge", "Shanghai Pudong", "2939335", 10000));
        m.addUser(new User("Chen Zi Ge", "Shanghai Pudong", "2939335", 10000));
        m.addUser(new User("Chen Zi Ge", "Shanghai Pudong", "2939335", 10000));
        m.addUser(new User("Chen Zi Ge", "Shanghai Pudong", "2939335", 10000));
        m.addUser(new User("Chen Zi Ge", "Shanghai Pudong", "2939335", 10000));
        m.addUser(new User("Chen Zi Ge", "Shanghai Pudong", "2939335", 10000));
        m.addUser(new User("Chen Zi Ge", "Shanghai Pudong", "2939335", 10000));
        m.addUser(new User("Chen Zi Ge", "Shanghai Pudong", "2939335", 10000));
        m.deleteUser(4);
        m.deleteUser(3);
        m.deleteUser(2);
        m.deleteUser(1);
        m.deleteUser(5);
        m.deleteUser(7);
        m.deleteUser(9);
        m.deleteUser(6);
        m.mergeAccounts(10,11);
        m.mergeAccounts(10,12);
        m.mergeAccounts(10,13);
        m.mergeAccounts(10,14);
        m.mergeAccounts(10,15);
        m.mergeAccounts(10,16);

        m.insertionSort(m.users);

        m.addLAUsers(new User("XiuYing Lin", "Room 5000", "21094810", 100000));
        m.addLAUsers(new User("XiuYing Lin", "Room 5000", "21094810", 100000));
        m.addLAUsers(new User("XiuYing Lin", "Room 5000", "21094810", 100000));
        m.addLAUsers(new User("XiuYing Lin", "Room 5000", "21094810", 100000));
        m.addLAUsers(new User("XiuYing Lin", "Room 5000", "21094810", 100000));
        m.addLAUsers(new User("XiuYing Lin", "Room 5000", "21094810", 100000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xiao Meng Jiang", "Room 5120", "2109481224", 5000));
        m.addLAUsers(new User("Xin Zhou", "Room 6666", "2109481224", 555555));

        Iterator<User> it = m.users.iterator();
        Iterator<User> it2 = m.freeUsers.iterator();
        while(it.hasNext()){
            User temp = it.next();
            System.out.println(temp.ID + " " + temp.Address + " " + temp.DepositAmount);
        }
        while(it2.hasNext()){
            User temp = it2.next();
            System.out.println("FreeUsers: " + temp.ID + " " + temp.Address + " " + temp.DepositAmount);
        }
        System.out.println(m.users.size() + " " + m.freeUsers.size());
        m.payUserToUser(20, 23, 3000);
        it = m.users.iterator();
        while(it.hasNext()){
            User temp = it.next();
            System.out.println(temp.ID + " " + temp.Address + " " + temp.DepositAmount);
        }
        double median = m.getMedianID();
        System.out.println(median + " " + m.bankOfLosAngeles.size());
        Iterator<User> it3 = m.bankOfLosAngeles.iterator();
        while(it3.hasNext()){
            User temp = it3.next();
            System.out.println(temp.ID + " " + temp.Address + " " + temp.DepositAmount);

        }
        m.mergeBanks(m.users,m.bankOfLosAngeles);
        Iterator<User> it4 = m.users.iterator();
        while(it4.hasNext()){
            User temp = it4.next();
            System.out.println(temp.ID + " " + temp.Address + " " + temp.DepositAmount);
        }

        System.out.println("Median ID is: " + m.getMedianID());


    }
}



