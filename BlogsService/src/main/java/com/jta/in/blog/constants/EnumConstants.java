package com.jta.in.blog.constants;

public class EnumConstants {

    /**
     * The Enum ResponseStatus.
     */
    public enum ResponseStatus {
        /**
         * The failure.
         */
        FAILURE("FAILURE"),
        /**
         * The success.
         */
        SUCCESS("SUCCESS"),
        WARNING("WARNING"),
        ERROR("ERROR");
        /**
         * The value.
         */
        private String value;

        /**
         * Instantiates a new response status.
         * @param value the value
         */
        ResponseStatus(String value) {
            this.value = value;
        }

        /**
         * Gets the value
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }

}
