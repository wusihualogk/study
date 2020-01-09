package org.saber.study.thread.t07;

/**
 * description:
 *
 * @author: saber
 * @date: 2019/12/30 11:03
 **/
public class ActionContext {

    private static final ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);

    public static Context get() {
        return context.get();
    }

    static class Context {
        private Configuration configuration;
        private OtherResource otherResource;

        public Configuration getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Configuration configuration) {
            this.configuration = configuration;
        }

        public OtherResource getOtherResource() {
            return otherResource;
        }

        public void setOtherResource(OtherResource otherResource) {
            this.otherResource = otherResource;
        }
    }

    static class Configuration {

    }

    static class OtherResource {

    }

}
