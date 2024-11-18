/******************************************************************
 *
 *   SYDNEY AMAYA / SECTION 001
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     *
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     *
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values        - int[] array to be sorted.
     * @param ascending     - if true,method performs an ascending sort, else descending.
     *                        There are two method signatures allowing this parameter
     *                        to not be passed and defaulting to 'true (or ascending sort).
     */

    public  void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending ) {
        int n = values.length;
        //ascending order
        for (int i = 0; i < n - 1; i++) {
                int min = i;
                for (int j = i + 1; j < n; j++){
                if (values[j] < values[min]){
                    min = j;
                }
            }
            if (min != i){
                //swap values[min] and values[j]
                int temp = values[i];
                values[i] = values[min];
                values[min] = temp;
            }
        }
        //descending order
        /**
         * swap each index with opposite index (length - i -1 )
         */
        if (ascending == false){
            for (int i = 0; i < n / 2; i++){
               int temp2 = values[i];
               values[i] = values[(n- i) - 1];
               values[(n- i) - 1] = temp2;
            }
         }
    } 


    /**
     *  Method mergeSortDivisibleByKFirst
     *
     *  This method will perform a merge sort algorithm. However, all numbers
     *  that are divisible by the argument 'k', are returned first in the sorted
     *  list. Example:
     *        values = { 10, 3, 25, 8, 6 }, k = 5
     *        Sorted result should be --> { 10, 25, 3, 6, 8 }
     *
     *        values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
     *        Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
     *
     * As shown above, this is a normal merge sort operation, except for the numbers
     * divisible by 'k' are first in the sequence.
     *
     * @param values    - input array to sort per definition above
     * @param k         - value k, such that all numbers divisible by this value are first
     */

    public void mergeSortDivisibleByKFirst(int[] values, int k) {

        // Protect against bad input values
        if (k == 0)  return;
        if (values.length <= 1)  return;

        mergeSortDivisibleByKFirst(values, k, 0, values.length-1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {

        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    /*
     * The merging portion of the merge sort, divisible by k first
     */

    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right){
        /**
         * merge and use an if statement to see if each element is divisible by k
         * and if it is add to the front of the array
         */
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int [] leftArray = new int [n1];
        int [] rightArray = new int [n2];
        for (int i = 0; i  < n1; i++){
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++){
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int kIndex = left;
        //sort elements in ascending order
        while ( i < n1 && j <n2){
            if (leftArray[i] % k == 0){
                arr[kIndex++] = leftArray[i++];
            }
            else if (rightArray[j] % k ==0){
                arr[kIndex++] = rightArray[j++];
            }
            else if(leftArray[i] <= rightArray[j] && leftArray[i] % k != 0 ){
                arr[kIndex++] = leftArray[i++];
            }
            else {
                arr[kIndex++] = rightArray[j++];
            }
        }
        while (i < n1){
            arr[kIndex++] = leftArray[i++];
        }
        while (j < n2){
            arr[kIndex++] = rightArray[j++];
        }
}


    /**
     * Method asteroidsDestroyed
     *
     * You are given an integer 'mass', which represents the original mass of a planet.
     * You are further given an integer array 'asteroids', where asteroids[i] is the mass
     * of the ith asteroid.
     *
     * You can arrange for the planet to collide with the asteroids in any arbitrary order.
     * If the mass of the planet is greater than or equal to the mass of the asteroid, the
     * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
     * planet is destroyed.
     *
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     *
     * Example 1:
     *   Input: mass = 10, asteroids = [3,9,19,5,21]
     *   Output: true
     *
     * Explanation: One way to order the asteroids is [9,19,5,3,21]:
     * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
     * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
     * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
     * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
     * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
     * All asteroids are destroyed.
     *
     * Example 2:
     *   Input: mass = 5, asteroids = [4,9,23,4]
     *   Output: false
     *
     * Explanation:
     * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
     * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
     * This is less than 23, so a collision would not destroy the last asteroid.
     *
     * Constraints:
     *     1 <= mass <= 105
     *     1 <= asteroids.length <= 105
     *     1 <= asteroids[i] <= 105
     *
     * @param mass          - integer value representing the mass of the planet
     * @param asteroids     - integer array of the mass of asteroids
     * @return              - return true if all asteroids destroyed, else false.
     */

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        /**
         * if the mass of the asteroid plus the sum of all the asteroids excluding the largest 
         * asteroid is greater than the mass of the largest asteroid return true otherwise
         * return false
         */
        //sort asteroids in ascending order
        Arrays.sort(asteroids);
        int sum = 0;
        boolean destroyed = false;
        //if asteroids has no elements the mass is automitcally greater so return true
        if (asteroids.length == 0){
            return true;
        }
        //add together all the elements excluding the last element which is the greatest asteroid
        for (int i = 0; i < asteroids.length - 1; i++){
            sum = sum + asteroids[i];
        }
        //see if mass + sum is greater than the mass of the largest asteroid
        if (mass + sum > asteroids[asteroids.length - 1]){
            destroyed = true;
        }
        return destroyed;

    }


    /**
     * Method numRescueSleds
     *
     * You are given an array people where people[i] is the weight of the ith person,
     * and an infinite number of rescue sleds where each sled can carry a maximum weight
     * of limit. Each sled carries at most two people at the same time, provided the
     * sum of the weight of those people is at most limit. Return the minimum number
     * of rescue sleds to carry every given person.
     *
     * Example 1:
     *    Input: people = [1,2], limit = 3
     *    Output: 1
     *    Explanation: 1 sled (1, 2)
     *
     * Example 2:
     *    Input: people = [3,2,2,1], limit = 3
     *    Output: 3
     *    Explanation: 3 sleds (1, 2), (2) and (3)
     *
     * Example 3:
     *    Input: people = [3,5,3,4], limit = 5
     *    Output: 4
     *    Explanation: 4 sleds (3), (3), (4), (5)
     *
     * @param people    - an array of weights for people that need to go in a sled
     * @param limit     - the weight limit per sled
     * @return          - the minimum number of rescue sleds required to hold all people
     */

    public static int numRescueSleds(int[] people, int limit) {
        /**
         * sort people using arrays.sort to get people in ascending order
         * use a for loop to add every two elements and if the sum is less than 
         * limit add one sled if it more than limit add two sleds
         */
        int sleds = 0;
        if (people.length == 0){
            return 0;
        }
        Arrays.sort(people);
        for (int i = 0; i < people.length / 2; i++){
            if (people[i] + people[i + 1] <= limit){
                sleds++;
            }
            else {
                sleds = sleds + 2; 
            }
        }
        return sleds;
    }

} // End Class ProblemSolutions

