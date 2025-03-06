# Chapter 1 - Refactoring: A First Example

우선 예제를 코틀린으로 작성하면서 왜 토비님이 1판의 Movie Rental/Video Store 예제가 더 깔끔하다고 말씀하셨는지 이해가 되었다. 밥 마틴이
리팩터링한 [결과](https://github.com/unclebob/videostore)를 보니 전체적으로 객체지향적으로 진행된 것을 볼 수 있었다. 그런데 도메인 설계가 없다는
점이 조금 이상했다. 아마 JSON 데이터, 즉 DTO를 그대로 사용해서 그런 것 같다. 그리고 그 DTO 중 하나인 `Performance`에 속성을 추가하여
`EnhancedPerformance`라고 부르는 것은 개인적으로 만족스럽지 않았다. 중첩 함수를 사용하는 것도 그렇다. 다행히 외부 맥락 상태를 변경하는 사이드 이펙트를
가진 “이상한” 중첩 함수는 없었지만, 그래도 객체지향적인 설계에 더 익숙하다 보니 중첩 함수가 없도록 계속 리팩터링했다. 아마 MF가 자바스크립트의 특성을
고려하여 그렇게 개발한 것 같기도 하다(결국 React도 ReasonML/OCaml 커뮤니티에서 나왔다는 점).

우선 Replace Temp with Query 기법을 사용해 임시 변수들을 대체하는 과도기적 리팩터링이 인상 깊었다. 나는 일반적으로 탐욕적으로 리팩터링을 해 왔기에,
일시적인 중복이나 중첩된 메서드 호출을 통해 나중에 메서드 추출이 더 용이하도록 하는 접근법을 미처 생각하지 못했었다. 함수의 파라미터를 변경해야 할 경우,
`method2()`를 만든 뒤 그 함수를 사용하는 클라이언트 코드를 모두 바꾼 다음에 기존 `method()`를 삭제하고 이름을 교체하는 식으로 멀리 돌아갔었는데,
Replace Temp with Query로 소거하려는 변수를 대체하면서 Change Function Declaration 기법도 함께 활용할 수 있다는 점이 흥미로웠다. 책 예제에서는
이런 방식으로 `amountFor(aPerformance, play)`라는 메서드 시그니처에서 `playFor(perf)` 쿼리를 통해 `play`를 제거했다.

계산 단계와 포매팅 단계를 분리하는 것은 좋았지만, `StatementData`에 들어가는 데이터가 그때그때 보이는 변수들을 단순히 모아놓은 것 같아 조금 아쉬웠다.
문득 [TDD는 설계 방법론이 아니다](https://gyuwon.github.io/blog/2019/03/03/tdd-is-not-a-design-methodology.html)라는 글이 떠올랐다.
언젠가 봐야지 하며 미뤄두었던 MF와 DHH의 [Is TDD is Dead?](https://martinfowler.com/articles/is-tdd-dead/)에도 그런 내용이 있을 것 같다.

다형성을 활용하여 `switch/when` 문의 구조를 개선하는 Replace Conditional with Polymorphism은 워낙 유명해서 복습하는 기분으로
즐겼다. 다만 `PerformanceCalculator`라는 객체 이름에서도 알 수 있듯이 여전히 절차지향적 프로세서 같은 느낌의 상속 구조를 만든 점이 조금 아쉬웠다.
물론 경우에 따라 특정 로직의 응집도를 고려해 일부러 절차지향적인 객체를 설계할 수도 있다는 것은 조영호님의 강의에서 `Validator` 예시로 배웠다. 추상 클래스
`Play`와 이를 구현하는 `Comedy`, `Tragedy`같은 구체 클래스를 만드는 방법은 어떨지 궁금하기도 하다.

마지막으로 Uncle Bob Martin도 ["Clean" Code, Horrible Performance
](https://www.computerenhance.com/p/clean-code-horrible-performance)라는 글의
저자와 [열띤 토론](https://github.com/unclebob/cmuratori-discussion)을 나눈 것으로 알고 있는데, 그때 Casey Muratori가 제기한 예 중
하나가 다형성 사용에 따른 성능 저하 문제였던 것 같다. 하지만 게임 클라이언트 개발자와 달리 서버 쪽은 스케일 아웃이 가능하므로, 야근을 줄이는 것이 더
중요하다고 개인적으로는 생각한다. IntelliJ IDEA의 기능들을 적극 활용하신
백명석님의 [Repeated Switch를 Polymorphic하게 리팩터링하기](https://www.youtube.com/watch?v=fUDvgApgySU) 영상도 비슷한 맥락에서 다시
보고 싶다.
