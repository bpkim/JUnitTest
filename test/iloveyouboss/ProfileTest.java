package iloveyouboss;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {
    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @Before
    public void create(){
        profile = new Profile("Bull Hocky, Inc.");
        question = new BooleanQuestion(1, "Get bonuses?");
        criteria = new Criteria();
        // 질문에 대한 적절한 답
        Answer profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

    }


    @Test
    // 함수명은 테스트의 내용을 알 수 있도록 한다
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet(){

        // 지원자의 답
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
        criteria.add(criterion);

        // Profile 클래스의 matches 함수 결과 리턴
        // 적절한 답은  Fasle 인데 지원자의 답은 True 이기때문에 false 가 나올것이다.
        boolean matches = profile.matches(criteria);

        // false 인지 확인
        assertFalse(matches);
    }



    @Test
    // 함수명은 테스트의 내용을 알 수 있도록 한다
    public void matchAnswersTrueWhenDontCareCriteriaNotMet(){

        // 지원자의 답
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
        criteria.add(criterion);

        // Profile 클래스의 matches 함수 결과 리턴
        // weight 가 Dont care 이기 때문에 결과 답이 달라도 true 나온다
        boolean matches = profile.matches(criteria);

        // true 인지 확인
        assertTrue(matches);
    }
}