package dev.be.membership.domain;

import lombok.*;


// Membership
// 오염이 되면 안되는 class, 핵심 domain
// 임의로 값의 수정, class 생성을 막음.
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 자동 생성된 생성자의 접근 권한은 class 내부로 한정시킴.
public class Membership {

    @Getter private final String membershipId;
    @Getter private final String name;
    @Getter private final String email;
    @Getter private final String address;
    @Getter private final boolean isValid;
    @Getter private final boolean isCorp;

    // Membership 객체의 생성은 각 field 에 대한 static final 생성 객체를 대입해야만 생성 가능.
    // 즉 임의의 값을 대입하는 것만으로는 해당 객체 생성은 불가능하므로, 보호의 정도를 높임
    //
    // ChatGPT 의 시나리오 예시:
    // 상상해 보면, Membership 객체를 생성할 때, 단순히 문자열 ID와 이름을 전달하는 것보다 MembershipId와 MembershipName 객체를 사용하는 것이 더 안전합니다.
    // 만약 MembershipId나 MembershipName에 특정 형식이나 규칙이 요구된다면, 이러한 객체들을 통해 생성하는 것이 유효성 검사와 같은 추가 로직을 적용하기 쉬워집니다.
    // 또한, 객체의 생성 로직을 generateMember 메서드에 중앙화함으로써, 이후에 이 로직을 변경하거나 확장해야 할 때 다른 코드에 영향을 미치지 않고 쉽게 수정할 수 있습니다.
    // 이런 방식은 특히 복잡한 객체 생성 규칙이나 비즈니스 로직을 가진 시스템에서 유용합니다.
    public static Membership generateMember(
            MembershipId membershipId,
            MembershipName membershipName,
            MembershipEmail membershipEmail,
            MembershipAddress membershipAddress,
            MembershipIsValid membershipIsValid,
            MembershipIsCorp membershipIsCorp
            ) {
        return new Membership(
                membershipId.membershipId,
                membershipName.membershipName,
                membershipEmail.membershipEmail,
                membershipAddress.membershipAddress,
                membershipIsValid.membershipIsValid,
                membershipIsCorp.membershipIsCorp
        );
    }


    // 해당 static class 를 통해서 membershipId 를 지정(생성)하고 이것을 통해서만
    // field 로 Membership 객체 생성 및 접근 (Getter only) 가능하게 함.
    //
    // @Value 적용시
    // sington pattern 중 하나로 내부의 모들 field 에 대해서 final 이 적용되어 생성 후 수정이 불가능해짐.
    // 또한 equals(), hashCode(), toString() 메서드가 자동으로 생성되어 객체의 동등성(equality)과 문자열 표현을 관리.
    // 클래스는 기본적으로 불변성을 가지며, 추가적인 설정 없이 Lombok이 처리
    @Value
    public static class MembershipId {
        String membershipId; // 해당 field 는 곧 Membership 객체 식별자로 사용됨.
        public MembershipId(String value) {
            this.membershipId = value;
        }
    }

    @Value
    public static class MembershipName {
        String membershipName; // 해당 field 는 곧 Membership 객체 식별자로 사용됨.
        public MembershipName(String value) {
            this.membershipName = value;
        }
    }

    @Value
    public static class MembershipEmail {
        String membershipEmail; // 해당 field 는 곧 Membership 객체 식별자로 사용됨.
        public MembershipEmail(String value) {
            this.membershipEmail = value;
        }
    }

    @Value
    public static class MembershipAddress {
        String membershipAddress; // 해당 field 는 곧 Membership 객체 식별자로 사용됨.
        public MembershipAddress(String value) {
            this.membershipAddress = value;
        }
    }

    @Value
    public static class MembershipIsValid {
        boolean membershipIsValid; // 해당 field 는 곧 Membership 객체 식별자로 사용됨.
        public MembershipIsValid(boolean value) {
            this.membershipIsValid = value;
        }
    }

    @Value
    public static class MembershipIsCorp {
        boolean membershipIsCorp; // 해당 field 는 곧 Membership 객체 식별자로 사용됨.
        public MembershipIsCorp(boolean value) {
            this.membershipIsCorp = value;
        }
    }
}
