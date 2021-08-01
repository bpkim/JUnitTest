package iloveyouboss;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AssertTest {


    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }


    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }
    }

    private Account account;

    @Before
    public void createAccount() {
        account = new Account("an account name");
    }

    /**
     * assertTrue ; True 인지 확인
     */
    @Test
    public void hasPositiveBalance(){
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }


    /**
     * assertFasle ; False 인지 확인
     */
    @Test
    public void depositIncreasesBalance(){
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertFalse(account.getBalance() < initialBalance);
    }

    /**
     * assertThat : 명확한 값 비교
     */
    @Test
    public void depositIncreasesBalanceAsseertThat() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertThat(account.getBalance() > initialBalance, is(true));
        assertThat(account.getBalance(), equalTo(100));

        // 실패 케이스
        assertThat(account.getName(), startsWith("xxx"));
    }


    @Test
    public void hamcrestMatcher(){
        // 클래스 비교
        assertThat(new String[]{"a", "b", "c"}, equalTo(new String[]{"a","b","c"}));
        assertThat(account.getName(), is(equalTo("an account name")));
        assertThat(account.getName(), equalTo("an account name"));
        assertThat(account.getName(), not(equalTo("zzz")));
        assertThat(account.getName(), is(not(nullValue())));
        assertThat(account.getName(), not(nullValue()));
        assertThat(account.getName(), is(notNullValue()));
    }



    @Test
    public void floatingPoint(){
        // fail
//        assertThat(2.32 * 3, equalTo(6.96));

        // float나 double를 비교할 때는 두 수가 벌어질 수 있는 공차 또는 허용 오차를 지정해야한다.
        // assertTure()를 사용하여 작성
        assertTrue(Math.abs((2.32 * 3) - 6.96) < 0.0005 );
    }

    @Test
    public void testWithWorthlessAssertionComment(){
        account.deposit(50);
        // 테스트 내용 코멘트 작성
        assertThat("account balance is 100", account.getBalance(), equalTo(50));
    }


    /**
     * 예외 처리
     */

    @Test(expected = InsufficientFundsException.class)
    public void throwsWhenWithdrawingTooMuch(){
        account.withdraw(100);
    }

    @Test
    public void throwsWhenWithdrawingTooMuchTryCatch(){
        try {
            account.withdraw(100);
            fail();
        }catch (InsufficientFundsException e){
            assertThat(e.getMessage(), equalTo("balance only 0"));
        }
    }

    /**
     * 에외가 발생하는지 테스트
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionRule(){
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage("balance only 0");

        account.withdraw(100);
    }
}
