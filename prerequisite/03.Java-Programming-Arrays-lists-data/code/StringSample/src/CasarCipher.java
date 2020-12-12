public class CasarCipher {
    protected final static  String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        CasarCipher example = new CasarCipher();
        String content = "Send chat messages on Zoom to either an individual user who is in your contact list or to a  of which you are a member. To send a message to a contact, provide the contact’s email address in the to_contact field. Similary, to send a message to a channel, provide the Channel Id of the Channel in to_channel field.\n" +
                "\n" +
                "Scopes: chat_message:write, chat_message:write:admin\n" +
                ": Medium\n";
        content = content.toUpperCase();
        example.encrypt(content, 17);
    }

    /**
     * Given a source message, encrypt it
     * @param{String} source the source message
     * @param{int} key letter shift amount
     * @return{String}
     */
    public void encrypt(String source, int key) {
        String shiftedAlphabet = this.getShiftedAlphabet(key);
        StringBuilder result = new StringBuilder("");
        int atIndex = 0;
        int strSourceLen = source.length();
        while (atIndex < strSourceLen) {
            char letter = source.charAt(atIndex);
            char newLetter = letter;
            int letterIndex = alphabet.indexOf(letter + "");
            if (letterIndex != -1) {
                newLetter = shiftedAlphabet.charAt(letterIndex);
            }
            result.append(newLetter);
            atIndex += 1;
        }

        System.out.println("For source=\n" + source
                + "\nafter encryption, the result = \n"
                + result.toString() + "\n");
    }

    /**
     * Given a key, get the shifted string for ABCDEFGHIJKLMNOPQRSTUVWXYZ
     *
     * @param{int} key
     * @return{StringBuildder}
     */
    public String getShiftedAlphabet(int key) {
        return alphabet.substring(key) + alphabet.substring(0,key);
    }
}
