/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package paging.android.example.com.cheeseapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import paging.android.example.com.cheeseapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
        private set
    private val viewModel by viewModels<CheeseViewModel> { CheeseViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = CheeseAdapter()
        binding.cheeseList.adapter = adapter

        lifecycleScope.launch {
            viewModel.allCheeses.collectLatest { adapter.submitData(it) }
        }

        initAddButtonListener()
        initSwipeToDelete()
    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val cheeseViewHolder = viewHolder as CheeseViewHolder
                return if (cheeseViewHolder.cheese != null) {
                    makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                } else {
                    makeMovementFlags(0, 0)
                }
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as CheeseViewHolder).cheese?.let {
                    viewModel.remove(it)
                }
            }
        }).attachToRecyclerView(binding.cheeseList)
    }

    private fun addCheese() {
        val newCheese = binding.inputText.text.trim()
        if (newCheese.isNotEmpty()) {
            viewModel.insert(newCheese)
            binding.inputText.setText("")
        }
    }

    private fun initAddButtonListener() {
        binding.addButton.setOnClickListener {
            addCheese()
        }

        // when the user taps the "Done" button in the on screen keyboard, save the item.
        binding.inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addCheese()
                return@setOnEditorActionListener true
            }
            false // action that isn't DONE occurred - ignore
        }
        // When the user clicks on the button, or presses enter, save the item.
        binding.inputText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addCheese()
                return@setOnKeyListener true
            }
            false // event that isn't DOWN or ENTER occurred - ignore
        }
    }
}
