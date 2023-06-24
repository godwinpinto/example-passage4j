import { PassageElement} from "@passageidentity/passage-auth/dist/package/index.js";
import '@passageidentity/passage-elements/passage-auth'


export default function Login() {

  const PASSAGE_APP_ID = import.meta.env.VITE_PASSAGE_APP_ID;

  console.log(PASSAGE_APP_ID);
  return (
    <>
    <div className="container">
      <h1>Passage4J + Java Frameworks Demo</h1>
      
      <passage-auth app-id={PASSAGE_APP_ID}></passage-auth>    
    </div>
    </>
  );
}
