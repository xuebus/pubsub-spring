package com.wanda.ffan.sub.listener;

import com.wanda.ffan.common.TopicPartition;

import java.util.Map;

/**
 * Created by zhangling on 2016/9/15.
 */
public interface ConsumerSeekAware {
    /**
     * Register the callback to use when seeking at some arbitrary time. When used with a
     * {@code ConcurrentMessageListenerContainer} or the same listener instance in multiple
     * containers listeners should store the callback in a {@code ThreadLocal}.
     * @param callback the callback.
     */
    void registerSeekCallback(ConsumerSeekCallback callback);

    /**
     * When using group management, called when partition assignments change.
     * @param assignments the new assignments and their current offsets.
     * @param callback the callback to perform an initial seek after assignment.
     */
    void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback);

    /**
     * If the container is configured to emit idle container events, this method is called
     * when the container idle event is emitted - allowing a seek operation.
     * @param assignments the new assignments and their current offsets.
     * @param callback the callback to perform a seek.
     */
    void onIdleContainer(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback);

    /**
     * A callback that a listener can invoke to seek to a specific offset.
     */
    interface ConsumerSeekCallback {

        /**
         * Queue a seek operation to the consumer. The seek will occur after any pending
         * offset commits. The consumer must be currently assigned the specified partition.
         * @param topic the topic.
         * @param partition the partition.
         * @param offset the offset (absolute).
         */
        void seek(String topic, int partition, long offset);

    }
}
