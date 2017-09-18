package mytry.mytry;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public abstract class Try<T> {

    public static <T> Try<T> of(Supplier<T> supplier) {
        try {
            return success(supplier.get());
        } catch (Throwable t) {
            return failure(t);
        }
    }

    public static <T, U> Try<U> of(final T t, Function<T, U> function) {
        try {
            return success(function.apply(t));
        } catch (Throwable t2) {
            return failure(t2);
        }
    }

    public static <T> Success<T> success(T value) {
        return new Success<>(value);
    }

    public static <T> Failure<T> failure(Throwable t) {
        return new Failure<>(t);
    }

    public boolean isSuccess() {
        return this instanceof Success;
    }

    public boolean isFailure() {
        return this instanceof Failure;
    }

    public Success<T> asSuccess() {
        return (Success<T>) this;
    }

    public Failure<T> asFailure() {
        return (Failure<T>) this;
    }

    public T get() {
        if (isFailure()) {
            Throwable t = this.asFailure().throwable;
            throw t instanceof RuntimeException ? (RuntimeException)t : new RuntimeException(t);
        } else {
            return asSuccess().value;
        }
    }

    public <U> U map(Function<T, U> mapper) {
        return mapper.apply(this.asSuccess().value);
    }

    public <U> Try<U> flatMap(Function<T, Try<U>> flatMapper) {
        if (this.isFailure())
            return new Failure<U>(this.asFailure().throwable);
        else {
            try {
                return flatMapper.apply(this.asSuccess().value);
            } catch (Throwable t) {
                return failure(t);
            }
        }
    }

    public <U> Try<U> tryMap(Function<T, U> mapper) {
        return flatMap((T t) -> Try.of(t, mapper));
    }

    public Try<T> onSuccess(Consumer<? super T> action) {
        if (isSuccess()) {
            action.accept(get());
        }
        return this;
    }

    public Try<T> onFailure(Consumer<? super Throwable> action) {
        if (isFailure()) {
            action.accept(this.asFailure().throwable);
        }
        return this;
    }

    public Try<T> onComplete(Consumer<? super T> successAction, Consumer<? super Throwable> failureAction) {
        return this.onSuccess(successAction).onFailure(failureAction);
    }


    public static final class Success<T> extends Try<T> {
        public final T value;

        public Success(T value) {
            this.value = value;
        }
    }

    public static final class Failure<T> extends Try<T> {
        public final Throwable throwable;

        public Failure(Throwable throwable) {
            this.throwable = throwable;
        }

        public String message() {
            return throwable.getMessage();
        }
    }
}
