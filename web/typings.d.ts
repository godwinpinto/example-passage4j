// typings.d.ts
import { PassageElement, PassageProfileElement } from '@passageidentity/passage-elements'
declare namespace JSX {
    interface IntrinsicElements {
        "passage-auth": PassageElement;
        "passage-login": PassageElement;
        "passage-register": PassageElement;
        "passage-profile": PassageProfileElement;
    }
}