package iloveyouboss;

// fail 메소드가 있다.
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
// Test 애너테이션이 있다.
import org.junit.*;

// 테스트할클래스명+Test 로 보통 명명 한다.
// 테스트할 클래스에 대해서 여러 테스트 클래스를 만드는 경우도 있다.
public class ScoreCollectionTest {

    // Test 애너테이션이 붙은 메소드는 테스트를 하는 메소드이다.
    // 테스트가 아닌 메소드도 포함될 수 있으며 JUnit은 그것들을 알아서 제외한다.
    @Test
    // 가장 중요한 정보 조각인 테스트 메서드의 이름은 기본적으로 test 이다.
    public void test() {
        // 실패를 일으키는 메소드
//        fail("Not yet implemented");
    }
    
    @Test
    public void answersArtithmeticMeanOfTwoNumbers(){
        // 준비 
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);
        
        // 실행
        int actualResult = collection.arithmeticMean();
        
        // 단언
        assertThat(actualResult, equalTo(6));
    }
}
