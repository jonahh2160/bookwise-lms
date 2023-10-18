
public abstract class Database {

    public Database() {};

    abstract void addEntry();

    abstract Book getBook(int id);
    abstract User getUser(int id);
    abstract Transaction getTransaction(int id);

    abstract void editEntry();

    abstract void removeEntry();
}
