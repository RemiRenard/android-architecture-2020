package remi.renard.singleactivitysample.domain.interactor

/**
 * Status of a resource that is provided to the UI.
 *
 * These are usually created by the Repository classes where they return `LiveData<Resource<T>>` to pass back the
 * latest data to the UI with its fetch status.
 */
enum class Status {

    /**
     * Corresponding to a loading state.
     */
    LOADING,

    /**
     * Corresponding to a success state.
     */
    SUCCESS,

    /**
     * Corresponding to an error state.
     */
    ERROR
}
