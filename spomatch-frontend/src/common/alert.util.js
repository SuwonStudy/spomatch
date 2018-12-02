import toastr from 'toastr'
import '../assets/css/toastr-style.css';
import 'toastr/build/toastr.min.css'

const option = {
    closeButton: true,
    onlyShowNewest: true,
    newestOnTop: false,
    escapeHtml: true,
    positionClass: 'toast-top-center',
    showMethod: 'slideDown',
    showEasing: 'linear',
    hideMethod: 'slideUp',
    showDuration: 300,
    hideDuration: 300,
    timeOut: 3000,
};

/**
 * Alert Util class
 */
class AlertUtil {

    /**
     * Success Alert
     * @param message
     */
     static success(message) {
        toastr.options = option;
        setTimeout(() => {
            toastr.success(message, 'Success');
        }, Math.random() * 1000);
    }

    /**
     * Warning Alert
     * @param message
     */
    static warning(message) {
        toastr.options = option;
        setTimeout(() => {
            toastr.warning(message, 'Warning');
        }, Math.random() * 1000);
    }

    /**
     * Error Alert
     * @param message
     */
    static error(message) {
        toastr.options = option;
        setTimeout(() => {
            toastr.error(message, 'Failed');
        }, Math.random() * 1000);
    }

    /**
     * Info Alert
     * @param message
     */
    static info(message) {
        toastr.options = option;
        setTimeout(() => {
            toastr.info(message, 'Information');
        }, Math.random() * 1000);
    }


}
export default AlertUtil;