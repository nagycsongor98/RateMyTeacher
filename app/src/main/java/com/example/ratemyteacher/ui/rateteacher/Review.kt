package com.example.ratemyteacher.ui.rateteacher

/**
 * @author  Csongor Nagy
 * @since  06.06.2021
 */

data class Review(
    var reviewerEmail: String,
    var reviewedTeacher: String,
    var reviewedDepartment: String,
    var reviewString: String) {
    constructor() : this("","","","")
}
